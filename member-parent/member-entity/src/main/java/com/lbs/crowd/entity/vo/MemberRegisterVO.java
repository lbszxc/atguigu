package com.lbs.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @date 2020/8/11 11:36
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRegisterVO {

    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    private String phoneNum;

    private String code;

}
