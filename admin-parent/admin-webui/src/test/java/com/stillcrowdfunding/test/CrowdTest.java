package com.stillcrowdfunding.test;

import com.stillcrowdfunding.entity.Admin;
import com.stillcrowdfunding.entity.Role;
import com.stillcrowdfunding.mapper.AdminMapper;
import com.stillcrowdfunding.mapper.RoleMapper;
import com.stillcrowdfunding.service.api.AdminService;
import com.stillcrowdfunding.service.api.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Administrator
 * @date 2020/6/12 17:43
 * @description
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleMapper roleMapper;


    @Test
    public void testRole(){
        for (int i = 0;i<10;i++){
            roleMapper.insert(new Role("role"+i));
        }
    }


    @Test
    public void test(){
        for (int i = 0 ; i<32 ;i++){
            adminMapper.insert(new Admin("loginAcount"+i,"userPassword"+i,"userName"+i,"email"+i,null));
        }
    }

    //测试阿里数据源是否与数据库配置成功
    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
    @Test
    public void testTx(){
        Admin admin = new Admin("tom","123456","汤姆","tom@163.com",null);
        adminService.saveAdmin(admin);
    }

    //测试Spring整合Mybatis数据库是否成功
    @Test
    public void testInsertAdmin(){
        Admin admin = new Admin("jack","123456","杰克","jack@163.com","null");
        int count = adminMapper.insert(admin);
        //如果在实际开发当中，所有想查看数值的地方都用sysout的方式打印，会给项目上线运行带来问题！
        //sysout本质上是一个IO流操作，通常IO流操作比较消耗性能的，如果项目sysout多了，那么对性能的影响就很大。
        //即使项目在上线前专门花时间删除代码中sysout，这样很可能遗漏，而且很麻烦。
        //而如果使用日志系统，那么通过日志级别就可以批量的控制信息的打印。
        System.out.println("受影响的行数："+count);
    }

    //日志系统日志的输出
    @Test
    public void testLogger(){

        //1.获取Logger对象，这里传入Class对象就是当前打印日志的类
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        //2.根据不同的日志级别打印日志
        logger.debug("Hello I am Debug level!!!");
        logger.debug("Hello I am Debug level!!!");

        logger.info("Info level!!!");
        logger.info("Info level!!!");

        logger.warn("Warn level!!!");
        logger.warn("Warn level!!!");

        logger.error("Error level!!!");
        logger.error("Error level!!!");
    }
}
