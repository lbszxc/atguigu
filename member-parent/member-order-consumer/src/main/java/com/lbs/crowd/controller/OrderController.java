package com.lbs.crowd.controller;

import com.lbs.crowd.api.MySQLRemoteService;
import com.lbs.crowd.entity.vo.AddressVO;
import com.lbs.crowd.entity.vo.MemberLoginVO;
import com.lbs.crowd.entity.vo.OrderProjectVO;
import com.stillcrowdfunding.constant.CrowdConstant;
import com.stillcrowdfunding.until.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/26 10:55
 * @description
 **/
@Controller
public class OrderController {

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    private Logger logger =  LoggerFactory.getLogger(OrderController.class);

    @RequestMapping(value = "/save/address")
    public String saveAddress(AddressVO addressVO,HttpSession session){

        // 1.执行地址信息的保存
        ResultEntity<String> resultEntity =  mySQLRemoteService.saveAddressRemote(addressVO);

        logger.debug("地址保存处理结果："+resultEntity.getResult());

        // 2.从Session域获取OrderProjectVO对象
        OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");

        // 3.从OrderProjectVO对象中获取returnCount
        Integer returnCount = orderProjectVO.getReturnCount();

        // 4.重定向到指定的地址，重新进入确定订单页面
        return "redirect:http://www.crowd.com/order/confirm/order/"+returnCount;

    }

    @RequestMapping(value = "/confirm/order/{returnCount}")
    public String showOrderConfirmInfo(
            @PathVariable("returnCount") Integer returnCount,
            HttpSession session){

        // 1.把接收到的回报数量合并到Session域
        OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");

        orderProjectVO.setReturnCount(returnCount);

        session.setAttribute("orderProjectVO",orderProjectVO);

        // 2.获取当前已经登录的用户ID
        MemberLoginVO memberLoginVO = (MemberLoginVO) session.getAttribute(CrowdConstant.ATTR_NMAE_LOGIN_MEMBER);

        Integer memberId = memberLoginVO.getId();

        // 3.查询现有的收货地址数据
        ResultEntity<List<AddressVO>> resultEntity = mySQLRemoteService.getAddressVORemote(memberId);

        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())){

            List<AddressVO> list = resultEntity.getData();

            session.setAttribute("addressVOList",list);

        }


        return "confirm-order";

    }




    @RequestMapping(value = "/confirm/return/info/{projectId}/{returnId}")
    public String showReturnConfirmInfo(
            @PathVariable("projectId") Integer projectId,
            @PathVariable("returnId") Integer returnId,
            HttpSession session){

        ResultEntity<OrderProjectVO> resultEntity = mySQLRemoteService.getOrderProjectVORemote(projectId,returnId);

        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())){

            OrderProjectVO  orderProjectVO = resultEntity.getData();

            //  为了能够在后续操作中保持orderProjectVO数据，存入Session域
            session.setAttribute("orderProjectVO",orderProjectVO);


        }

        return "confirm_return";

    }

}
