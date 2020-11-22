package com.lbs.crowd.controller;

import com.lbs.crowd.entity.po.MemberPO;
import com.lbs.crowd.service.api.MemberService;
import com.stillcrowdfunding.constant.CrowdConstant;
import com.stillcrowdfunding.until.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2020/8/6 16:45
 * @description
 **/
@RestController
public class MemberProviderController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/save/memberpo/remote")
    public ResultEntity<String> saveMember(@RequestBody MemberPO memberPO){


        try {

            memberService.saveMember(memberPO);

            return ResultEntity.successWithoutData();

        } catch (Exception e) {

            if (e instanceof DuplicateKeyException){

                return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);

            }

            return ResultEntity.failed(e.getMessage());

        }

    }

    @RequestMapping(value = "/get/memberpo/by/login/acct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct){

        try {
            // 1.调用本地Service完成查询
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginacct);

            // 2.如果没有抛异常，那么就返回成功的结果
            return ResultEntity.successWithoutData(memberPO);

        } catch (Exception e) {

            e.printStackTrace();

            // 3.如果捕获到异常则返回失败的结果
            return ResultEntity.failed(e.getMessage());
        }
    }

}
