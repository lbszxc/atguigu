package com.lbs.crowd.controller;

import com.lbs.crowd.entity.vo.DetailProjectVO;
import com.lbs.crowd.entity.vo.PortalTypeVO;
import com.lbs.crowd.entity.vo.ProjectVO;
import com.lbs.crowd.service.api.ProjectService;
import com.stillcrowdfunding.until.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/15 11:00
 * @description
 **/
@RestController
public class ProjectProviderController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/get/project/detail/remote/{projectId}")
    public ResultEntity<DetailProjectVO> getProjectDetailRemote(@PathVariable("projectId") Integer projectId){

        try {
            DetailProjectVO detailProjectVO = projectService.getDetailProjectVO(projectId);

            return ResultEntity.successWithoutData(detailProjectVO);

        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

    }

    @RequestMapping(value = "/get/portal/type/project/data/remote")
    public ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote(){

        try {
            List<PortalTypeVO> portalTypeVOList =  projectService.getPortalTypeVO();

            return ResultEntity.successWithoutData(portalTypeVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }

    @RequestMapping(value = "save/project/vo/remote")
    public ResultEntity<String> saveProjectVORemote(
            @RequestBody ProjectVO projectVO,
            @RequestParam("memberId") Integer memberId){

        try {
            // 调用“本地”的Service执行保存
            projectService.saveProject(projectVO,memberId);

            return ResultEntity.successWithoutData();

        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }

}
