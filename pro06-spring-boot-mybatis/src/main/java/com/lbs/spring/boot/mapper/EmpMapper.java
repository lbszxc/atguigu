package com.lbs.spring.boot.mapper;

import com.lbs.spring.boot.Emp;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/7/31 12:23
 * @description
 **/
public interface EmpMapper {

    List<Emp> selectAll();

}
