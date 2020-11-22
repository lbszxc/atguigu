package com.stillcrowdfunding.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stillcrowdfunding.constant.CrowdConstant;
import com.stillcrowdfunding.entity.Admin;
import com.stillcrowdfunding.entity.AdminExample;
import com.stillcrowdfunding.entity.AdminExample.Criteria;
import com.stillcrowdfunding.exception.LoginAccountAlreadyInUseForUpdateException;
import com.stillcrowdfunding.exception.LoginFailedException;
import com.stillcrowdfunding.mapper.AdminMapper;
import com.stillcrowdfunding.exception.LoginAccountAlreadyInUseException;
import com.stillcrowdfunding.service.api.AdminService;
import com.stillcrowdfunding.until.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Administrator
 * @date 2020/6/13 19:29
 * @description
 **/
@Service
public class AdminServiceImpl implements AdminService {
   @Autowired
   private AdminMapper adminMapper;

   @Autowired
   private BCryptPasswordEncoder passwordEncoder;

   private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Override
    public void saveAdmin(Admin admin) {

        // 1.密码加密
        String userPassword = admin.getUserPassword();

        //MD5加密
        //userPassword = CrowdUtil.md5(userPassword);
        //带盐值加密
        userPassword = passwordEncoder.encode(userPassword);

        admin.setUserPassword(userPassword);

        // 2.生成创建时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(date);
        admin.setCreateTime(createTime);

        // 3.执行保存
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();

            logger.info("异常类的全类名："+e.getClass().getName());

            if(e instanceof DuplicateKeyException){

                throw new LoginAccountAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);

            }
        }
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {

        // 1.根据登录账号查询Admin对象
            // ①.创建AdminExample对象
            AdminExample adminExample = new AdminExample();

            // ②.创建Criteria对象
            Criteria criteria = adminExample.createCriteria();

            // ③.在Criteria对象中封装查询条件loginAcct
            criteria.andLoginAcountEqualTo(loginAcct);

            // ④.调用AdminMapper方法进行查询
            List<Admin> adminList =  adminMapper.selectByExample(adminExample);

        // 2.判断Admin对象是否为null
        if (adminList == null || adminList.size() == 0){

            throw  new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);

        }

        if (adminList.size() > 1){

            throw  new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);

        }

        // 3.如果Admin对象为null，则抛出异常
        Admin admin = adminList.get(0);

        if (admin == null){

            throw  new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);

        }

        // 4.如果Admin对象不为null，就将数据库密码从Admin对象中取出
        String userPasswordDB= admin.getUserPassword();

        // 5.将表单提交的明文密码进行加密
        String userPasswordForm = CrowdUtil.md5(userPswd);

        // 6.将密码进行比较
        if (!Objects.equals(userPasswordDB,userPasswordForm)){

            // 7.如果密码不一致，则抛出异常
            throw  new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);

        }

        // 8.如果密码一致，则返回Admin对象
        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        // 1.调用PageHelper的静态方法开启分页功能
        // 这里充分体现了PageHelper的“非侵入式”设计，原本要查询的数据不必有任何的修改
        PageHelper.startPage(pageNum,pageSize);

        // 2.执行查询
        List<Admin> adminList = adminMapper.selectAdminByKeyword(keyword);

        // 3.封装到PageInfo对象中
        return new PageInfo<>(adminList);
    }

    @Override
    public void removeAdmin(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public void updateAdmin(Admin admin) {

        // “Selective”表示有选择的更新，对于null值字段的不更新
        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            e.printStackTrace();

            logger.info("异常类的全类名："+e.getClass().getName());

            if(e instanceof DuplicateKeyException){

                throw new LoginAccountAlreadyInUseForUpdateException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);

            }

        }

    }

    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {

        // 为了简化操作：先根据adminId删除旧的数据，再根据roleIdList保存全部新数据

        // 1.根据adminId删除旧的关联关系数据
        adminMapper.deleteOldRelationship(adminId);

        // 2.根据roleIdList和adminId保存新的关联关系数据
        if (roleIdList != null && roleIdList.size() > 0){
            adminMapper.insertNewRelationship(adminId,roleIdList);
        }

    }

    @Override
    public Admin getAdminByLoginAcct(String username) {

        AdminExample example = new AdminExample();

        Criteria criteria = example.createCriteria();

        criteria.andLoginAcountEqualTo(username);

        List<Admin> list = adminMapper.selectByExample(example);

        Admin admin = list.get(0);

        return admin;
    }
}
