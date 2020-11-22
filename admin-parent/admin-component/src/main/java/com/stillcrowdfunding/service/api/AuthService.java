package com.stillcrowdfunding.service.api;

import com.stillcrowdfunding.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020/7/22 17:20
 * @description
 **/
public interface AuthService {
    List<Auth> getAll();

    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    void saveRoleAuthRelationship(Map<String, List<Integer>> map);

    List<String> getAssignedAuthNameByAdminId(Integer adminId);
}
