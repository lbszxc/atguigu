package com.lbs.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/18 20:34
 * @description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortalTypeVO {

    private Integer id;
    private String name;
    private String remark;
    private List<PortalProjectVO> portalProjectVOList;

}
