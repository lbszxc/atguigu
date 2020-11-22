package com.stillcrowdfunding.service.api;

import com.github.pagehelper.PageInfo;
import com.stillcrowdfunding.entity.Role;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/6/23 21:32
 * @description
 **/
public interface RoleService{

    PageInfo<Role> getPageInfo(Integer pageNum,Integer pageSize,String keyword);

    void saveRole(Role role);

    void updateRole(Role role);

    void removeRole(List<Integer> roleIdList);

    List<Role> getAssignedRole(Integer adminId);

    List<Role> getUnAssignedRole(Integer adminId);
}
