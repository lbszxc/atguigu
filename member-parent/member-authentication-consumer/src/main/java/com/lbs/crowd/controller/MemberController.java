package com.lbs.crowd.controller;

import com.lbs.crowd.api.MySQLRemoteService;
import com.lbs.crowd.api.RedisRemoteService;
import com.lbs.crowd.config.ShortMessageProperties;
import com.lbs.crowd.entity.po.MemberPO;
import com.lbs.crowd.entity.vo.MemberLoginVO;
import com.lbs.crowd.entity.vo.MemberRegisterVO;
import com.stillcrowdfunding.constant.CrowdConstant;
import com.stillcrowdfunding.until.CrowdUtil;
import com.stillcrowdfunding.until.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2020/8/9 14:27
 * @description
 **/
@Controller
public class MemberController {

    @Autowired
    private ShortMessageProperties messageProperties;

    @Autowired
    private RedisRemoteService redisRemoteService;

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping(value = "/auth/member/to/logout")
    public String logoutMember(HttpSession session){

        session.invalidate();

        return "redirect:http://www.crowd.com:50/";

    }

    @RequestMapping(value = "/auth/member/do/login")
    public String loginMember(@RequestParam("loginacct") String loginacct,
                              @RequestParam("userpswd") String userpswd,
                              ModelMap modelMap,
                              HttpSession session){

        // 1.调用远程的接口根据登录账号查询MemberPO对象
        ResultEntity<MemberPO> resultEntity =  mySQLRemoteService.getMemberPOByLoginAcctRemote(loginacct);

        if (ResultEntity.FAILED.equals(resultEntity.getResult())){

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,resultEntity.getMessage());

            return "member-login";

        }

        MemberPO memberPO = resultEntity.getData();

        if (memberPO == null){

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_LOGIN_FAILED);

            return "member-login";

        }

        // 2.比较密码
        String userPswdDataBase = memberPO.getUserpswd();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean matchesResult = passwordEncoder.matches(userpswd,userPswdDataBase);

        if (!matchesResult){

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_LOGIN_FAILED);

            return "member-login";

        }

        // 3.创建MemberLoginVO对象存入Session域里
        MemberLoginVO memberLoginVO = new MemberLoginVO(memberPO.getId(),memberPO.getUsername(),memberPO.getEmail());

        session.setAttribute(CrowdConstant.ATTR_NMAE_LOGIN_MEMBER,memberLoginVO);

        return "redirect:http://www.crowd.com/auth/member/to/center/page";
    }

    @RequestMapping(value = "/auto/member/do/register")
    public String registerMember(MemberRegisterVO memberRegisterVO, ModelMap modelMap){

        // 1.获取用户输入的手机号
        String phoneNum = memberRegisterVO.getPhoneNum();

        // 2.拼接Redis中存储验证码的Key
        String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;

        // 3.从Redis的Key中读取对应的Value值
        ResultEntity<String> resultEntity =  redisRemoteService.getRedisStringValueByKeyRemote(key);

        // 4.检查查询操作是否有效
        String result = resultEntity.getResult();

        if (ResultEntity.FAILED.equals(result)){

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,resultEntity.getMessage());

            return "member-reg";

        }

        String redisCode = resultEntity.getData();

        if (redisCode == null){

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_CODE_NOT_EXIST);

            return "member-reg";

        }
        // 5.如果从Redis能够查询到Value，则比较表单的验证码和Redis中存储的验证码
        String fromCode = memberRegisterVO.getCode();

        if (!Objects.equals(fromCode,redisCode)){

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_CODE_INVALID);

            return "member-reg";

        }
        // 6.比较一致，则从Redis中删除
        redisRemoteService.removeRedisKeyRemote(key);

//        if (ResultEntity.FAILED.equals(removeResultEntity.getResult())){
//
//            return "";
//
//        }

        // 7.执行密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String userPswdBeforeEncode = memberRegisterVO.getUserpswd();

        String userPswdAfterEncode = passwordEncoder.encode(userPswdBeforeEncode);

        memberRegisterVO.setUserpswd(userPswdAfterEncode);

        // 8.执行保存
        // ①创建一个空的MemberPO对象
        MemberPO memberPO = new MemberPO();

        // ②复制属性
        BeanUtils.copyProperties(memberRegisterVO,memberPO);

        // s③调用远程的方法
        ResultEntity<String> saveMemberResultEntity =  mySQLRemoteService.saveMember(memberPO);

        if (ResultEntity.FAILED.equals(saveMemberResultEntity.getResult())){


            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,saveMemberResultEntity.getMessage());

            return "member-reg";

        }

        // 使用重定向避免刷新浏览器导致重新执行注册流程
        return "redirect:/auth/member/to/login/page";

    }

    @ResponseBody
    @RequestMapping(value = "/auto/member/send/short/message.json")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum){

        // 1.发送验证码到phoneNum手机
        ResultEntity<String> sendMessageResultEntity = CrowdUtil.sendCodeByShortMessage(
                messageProperties.getHost(),
                messageProperties.getPath(),
                messageProperties.getPhoneNum(),
                messageProperties.getAppCode(),
                messageProperties.getSign(),
                messageProperties.getSkin()
        );

       // 2.判断短信发送的结果
       if (ResultEntity.SUCCESS.equals(sendMessageResultEntity.getResult())){
           // 3.如果发送成功，则将验证码存入Redis
           // ①从上一步的操作结果中获取随机生成的验证码
           String code = sendMessageResultEntity.getData();

           // ②拼接一个Rides中存储的key
           String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;

           // ③调用远程的接口存入Redis
           ResultEntity<String> saveCodeResultEntity = redisRemoteService.setRedisKeyValueRemoteWithTimeout(key,code,15, TimeUnit.MINUTES);

           // ④判断结果
           if (ResultEntity.SUCCESS.equals(saveCodeResultEntity.getResult())){

               return ResultEntity.successWithoutData();

           }else {

               return saveCodeResultEntity;
           }

       }else {

           return sendMessageResultEntity;

       }

    }
}
