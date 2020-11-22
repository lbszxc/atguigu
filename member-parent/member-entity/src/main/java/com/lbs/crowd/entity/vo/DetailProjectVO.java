package com.lbs.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/20 10:39
 * @description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailProjectVO {

    private Integer projectId;

    private String projectName;

    private String projectDesc;

    private Integer followerCount;

    private Integer status;

    private Integer day;

    private String statusText;

    private Integer money;

    private Integer supportMoney;

    private Integer percentage;

    private String deployDate;

    private Integer lastDay;

    private Integer supporterCount;

    private String headerPicturePath;

    private List<String> detailPicturePathList;

    private List<DetailReturnVO> detailReturnVOList;



}
