package com.lbs.crowd.controller;

import com.lbs.crowd.api.MySQLRemoteService;
import com.lbs.crowd.config.OSSProperties;
import com.lbs.crowd.entity.vo.*;
import com.netflix.ribbon.proxy.annotation.Http;
import com.stillcrowdfunding.constant.CrowdConstant;
import com.stillcrowdfunding.until.CrowdUtil;
import com.stillcrowdfunding.until.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Administrator
 * @date 2020/8/15 17:55
 * @description
 **/
@Controller
public class ProjectConsumerController {

    @Autowired
    private OSSProperties ossProperties;

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping(value = "/get/project/detail/{projectId}")
    public String getProjectDetail(@PathVariable("projectId") Integer projectId, Model model){

        ResultEntity<DetailProjectVO> resultEntity = mySQLRemoteService.getProjectDetailRemote(projectId);

        String result =  resultEntity.getResult();

        if (ResultEntity.SUCCESS.equals(result)){

            DetailProjectVO detailProjectVO = resultEntity.getData();

            model.addAttribute("detailProjectVO",detailProjectVO);


        }

        return "project-show-detail";
    }

    @RequestMapping(value = "/create/confirm")
    public String saveConfirm(HttpSession session, MemberConfirmInfoVO confirmInfoVO,ModelMap modelMap){

        // 1.从Session 域读取之前临时存储的ProjectVO 对象
        ProjectVO projectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT);

        // 2.判断ProjectVO对象是否为null
        if (projectVO == null){

            throw new RuntimeException(CrowdConstant.MESSAGE_TEMPLE_PROJECT_MISSING);

        }

        // 3.将确认信息数据设置到projectVO 对象中
        projectVO.setMemberConfirmInfoVO(confirmInfoVO);

        // 4.从Session 域读取当前登录的用户
        MemberLoginVO memberLoginVO = (MemberLoginVO) session.getAttribute(CrowdConstant.ATTR_NMAE_LOGIN_MEMBER);

        Integer memberId = memberLoginVO.getId();

        ResultEntity<String> saveResultEntity = mySQLRemoteService.saveProjectVORemote(projectVO,memberId);

        // 6.判断远程的保存操作是否成功
        String saveResult = saveResultEntity.getResult();
        if (ResultEntity.FAILED.equals(saveResult)){

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,saveResultEntity.getMessage());

            return "project-confirm";

        }

        // 7.将临时的ProjectVO 对象从Session 域移除
        session.removeAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT);

        // 8.如果远程保存成功则跳转到最终完成页面
        return "redirect:http://www.crowd.com/project/create/success";
    }


    @ResponseBody
    @RequestMapping(value = "/create/save/return.json")
    public ResultEntity<String> saveReturn(ReturnVO returnVO, HttpSession session){

        try {
            // 1.从Session域中读取之前缓存的ProjectVO对象
            ProjectVO projectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT);

            // 2.判断ProjectVO对象是否为null
            if (projectVO == null){

                return ResultEntity.failed(CrowdConstant.MESSAGE_TEMPLE_PROJECT_MISSING);

            }

            // 3.从ProjectVO对象中获取存储回报信息的集合
            List<ReturnVO> returnVOList = projectVO.getReturnVOList();

            // 4.判断returnVOList集合是否有效
            if (returnVOList == null || returnVOList.size() == 0){

                // 5.创建集合对象对returnVOList进行初始化
                returnVOList = new ArrayList<>();

                // 6.为了让以后能够正常使用这个集合，设置到projectVO对象中
                projectVO.setReturnVOList(returnVOList);

            }

            // 7.将收集了表单数据的returnVO 对象存入集合
            returnVOList.add(returnVO);

            // 8.把数据有变化的ProjectVO对象重新存入Session域，以确保新的数据最终能够存入Redis
            session.setAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT,projectVO);

            // 9.所有操作成功完成返回成功
            return ResultEntity.successWithoutData();
        } catch (Exception e) {

            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }
    }
    // JavaScript代码：formData.append("returnPicture", file);
    // returnPicture是请求参数的名字
    // file是请求参数的值
    @ResponseBody
    @RequestMapping(value = "/create/upload/return/picture.json")
    public ResultEntity<String> uploadReturnPicture(

            // 接收用户上传的文件
            @RequestParam("returnPicture") MultipartFile returnPicture) throws IOException {

            // 1.执行上传的文件
        ResultEntity<String> returnPicResultEntity =  CrowdUtil.uploadFileToOss(
                ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                returnPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                returnPicture.getOriginalFilename());

        // 2.返回上传的结果
        return returnPicResultEntity;

    }

   @RequestMapping(value = "/create/project/information")
   public String saveProjectBasicInfo(

           // 接收除了上传图片之外的其他普通数据
           ProjectVO projectVO,

           // 接收上传的头图
           MultipartFile headerPicture,

           // 接收上传的详情图片
           List<MultipartFile> detailPictureList,

           // 用来将收集了一部分数据的ProjectVO 对象存入Session域
           HttpSession session,

           // 用来在当前操作失败后返回上一个表单页面时携带提示消息
           ModelMap modelMap) throws IOException {


       // 一.完成头图上传
       // 1.获取当前headerPicture对象是否为空
       boolean headerPictureIsEmpty = headerPicture.isEmpty();

       if (headerPictureIsEmpty){

           // 2.如果没有上传头图则返回到表单页面并显示错误消息
           modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_HEADER_PIC_EMPTY);

           return "project-launch";

       }

       // 3.如果用户确实上传了有内容的文件，则执行上传
       ResultEntity<String> uploadPicResultEntity =  CrowdUtil.uploadFileToOss(
               ossProperties.getEndPoint(),
               ossProperties.getAccessKeyId(),
               ossProperties.getAccessKeySecret(),
               headerPicture.getInputStream(),
               ossProperties.getBucketName(),
               ossProperties.getBucketDomain(),
               headerPicture.getOriginalFilename());

       String uploadPicResult = uploadPicResultEntity.getResult();

       // 4.判断头图是否上传成功
       if (ResultEntity.SUCCESS.equals(uploadPicResult)){

           // 5.如果成功则从返回的数据中获取图片访问路径
           String headerPicturePath = uploadPicResultEntity.getData();

           // 6.存入ProjectVO 对象中
           projectVO.setHeaderPicturePath(headerPicturePath);

       }else {

           // 7.如果上传失败则返回到表单页面并显示错误消息
           modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_HEADER_PIC_UPLOAD_FAILED);

           return "project-launch";
       }


       // 一.完成详情图片上传
       // 1.创建一个用来存放详情图片路径的集合
       List<String> detailPicturePathList = new ArrayList<>();

       // 2.检查detailPicturePathList是否有效
       if (detailPictureList == null || detailPictureList.size() == 0){

           modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_DETAIL_PIC_EMPTY);

           return "project-launch";

       }

       // 3.遍历detailPictureList 集合
       for(MultipartFile detailPicture:detailPictureList){

            // 4.当前detailPicture 是否为空
            if (detailPicture.isEmpty()){

                // 5.检测到详情图片中单个文件为空也是回去显示错误消息
                modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_DETAIL_PIC_EMPTY);

                return "project-launch";

            }


           // 6.执行上传
           ResultEntity<String> detailUploadPicResultEntity =  CrowdUtil.uploadFileToOss(
                   ossProperties.getEndPoint(),
                   ossProperties.getAccessKeyId(),
                   ossProperties.getAccessKeySecret(),
                   detailPicture.getInputStream(),
                   ossProperties.getBucketName(),
                   ossProperties.getBucketDomain(),
                   detailPicture.getOriginalFilename());

           // 7.检查上传结果
           String detailUploadPicResult = detailUploadPicResultEntity.getResult();

           if (ResultEntity.SUCCESS.equals(detailUploadPicResult)){

               String  detailPicturePath= detailUploadPicResultEntity.getData();

               // 8.收集刚刚上传的图片的访问路径
               detailPicturePathList.add(detailPicturePath);

           }else {

               // 8.如果上传失败则返回到表单页面并显示错误消息
               modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_DETAIL_PIC_UPLOAD_FAILED);

               return "project-launch";
           }

       }

       // 9.将存放了详情图片访问路径的集合存入ProjectVO 中
       projectVO.setDetailPicturePathList(detailPicturePathList);

       // 三、后续操作
       // 1.将ProjectVO对象存入Session域
       session.setAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT,projectVO);

       // 2.以完整的路径重定向到回报设置页面
       return "redirect:http://www.crowd.com/project/return/info/page";

   }
}
