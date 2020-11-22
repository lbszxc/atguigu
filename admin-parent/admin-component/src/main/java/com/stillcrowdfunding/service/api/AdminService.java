package com.stillcrowdfunding.service.api;

import com.github.pagehelper.PageInfo;
import com.stillcrowdfunding.entity.Admin;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/6/13 19:29
 * @description
 **/
public interface AdminService {
    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    void removeAdmin(Integer adminId);

    Admin getAdminById(Integer adminId);

    void updateAdmin(Admin admin);

    void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList);

    Admin getAdminByLoginAcct(String username);
}
