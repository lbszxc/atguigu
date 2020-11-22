package com.lbs.crowd.test;

import com.lbs.crowd.entity.po.MemberPO;
import com.lbs.crowd.entity.vo.DetailProjectVO;
import com.lbs.crowd.entity.vo.DetailReturnVO;
import com.lbs.crowd.entity.vo.PortalProjectVO;
import com.lbs.crowd.entity.vo.PortalTypeVO;
import com.lbs.crowd.mapper.MemberPOMapper;
import com.lbs.crowd.mapper.ProjectPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/6 10:19
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Autowired
    private ProjectPOMapper projectPOMapper;

    private Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Test
    public void testLoadDetailProjectVO(){

        Integer projectId = 14;

        DetailProjectVO detailProjectVO =  projectPOMapper.selectDetailProjectVO(projectId);

        logger.info(detailProjectVO.getProjectId()+ "");
        logger.info(detailProjectVO.getProjectName());
        logger.info(detailProjectVO.getMoney()+"");
        logger.info(detailProjectVO.getProjectDesc());
        logger.info(detailProjectVO.getFollowerCount()+"");
        logger.info(detailProjectVO.getSupportMoney()+ "");
        logger.info(detailProjectVO.getStatus()+ "");
        logger.info(detailProjectVO.getSupporterCount()+ "");
        logger.info(detailProjectVO.getHeaderPicturePath());
        logger.info(detailProjectVO.getPercentage()+"");
        logger.info(detailProjectVO.getDeployDate());

        List<String> detailProjectVOList = detailProjectVO.getDetailPicturePathList();

        for (String path : detailProjectVOList){

            logger.info("path："+path);

        }

        List<DetailReturnVO> detailReturnVOList = detailProjectVO.getDetailReturnVOList();

        for (DetailReturnVO detailReturnVO : detailReturnVOList){

            logger.info(detailReturnVO.getReturnId()+"");
            logger.info(detailReturnVO.getSupportMoney()+"");
            logger.info(detailReturnVO.getSignalPurchase()+"");
            logger.info(detailReturnVO.getPurchase()+"");
            logger.info(detailReturnVO.getSupporterCount()+"");
            logger.info(detailReturnVO.getFreight()+"");
            logger.info(detailReturnVO.getReturnDate()+"");
            logger.info(detailReturnVO.getContent());


        }


    }

    @Test
    public void testLoadTypeData(){

        List<PortalTypeVO> typeVOList =  projectPOMapper.selectPortalTypeVOList();

        for (PortalTypeVO portalTypeVO : typeVOList){

            String name = portalTypeVO.getName();
            String remark = portalTypeVO.getRemark();
            logger.info("Name："+name+" "+"Remark："+remark);

            List<PortalProjectVO> portalProjectVOList = portalTypeVO.getPortalProjectVOList();

            for (PortalProjectVO portalProjectVO : portalProjectVOList){

                if (portalProjectVO == null){
                    continue;
                }

                logger.info(portalProjectVO.toString());

            }


        }



    }

    @Test
    public void testMapper(){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String source = "123456";

        String encode = passwordEncoder.encode(source);

        MemberPO memberPO = new MemberPO(null,"jack",encode,"杰克","jack@qq.com",1,1,"杰克","341208",2);

        memberPOMapper.insert(memberPO);
    }

    @Test
    public void testConnection() throws SQLException {

        Connection connection = dataSource.getConnection();

        logger.debug(connection.toString());

    }

}
