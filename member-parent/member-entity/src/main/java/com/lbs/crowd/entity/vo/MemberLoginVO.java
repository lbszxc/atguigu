package com.lbs.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2020/8/11 21:57
 * @description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginVO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String email;

}
