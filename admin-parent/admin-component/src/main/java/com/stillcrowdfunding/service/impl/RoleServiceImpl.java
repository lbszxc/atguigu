package com.stillcrowdfunding.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stillcrowdfunding.entity.Role;
import com.stillcrowdfunding.entity.RoleExample;
import com.stillcrowdfunding.mapper.RoleMapper;
import com.stillcrowdfunding.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/6/23 21:32
 * @description
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {

        // 1.调用PageHelper的静态方法开启分页功能
        // 这里充分体现了PageHelper的“非侵入式”设计，原本要查询的数据不必有任何的修改
        PageHelper.startPage(pageNum,pageSize);

        // 2.执行查询
        List<Role> roles = roleMapper.selectRoleByKeyword(keyword);

        // 3.封装返回到PageInfo对象中
        return new PageInfo<>(roles);
    }

    @Override
    public void saveRole(Role role){
        roleMapper.insert(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void removeRole(List<Integer> roleIdList) {

        RoleExample example = new RoleExample();

        RoleExample.Criteria criteria = example.createCriteria();

        // 自动生成SQL语句delete from t_role where id in (5,8,12)
        criteria.andIdIn(roleIdList);

        roleMapper.deleteByExample(example);
    }

    @Override
    public List<Role> getAssignedRole(Integer adminId) {

        return roleMapper.selectAssignRole(adminId);
    }

    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {
        return roleMapper.selectUnAssignRole(adminId);
    }
}
