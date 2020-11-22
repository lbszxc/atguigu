package com.lbs.crowd.service.api;

import com.lbs.crowd.entity.vo.DetailProjectVO;
import com.lbs.crowd.entity.vo.PortalProjectVO;
import com.lbs.crowd.entity.vo.PortalTypeVO;
import com.lbs.crowd.entity.vo.ProjectVO;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/15 11:01
 * @description
 **/
public interface ProjectService {

    void saveProject(ProjectVO projectVO, Integer memberId);

    List<PortalTypeVO> getPortalTypeVO();

    DetailProjectVO getDetailProjectVO(Integer projectId);

}
