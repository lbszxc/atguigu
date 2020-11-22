package com.lbs.crowd.controller;

import com.lbs.crowd.entity.po.OrderProjectPO;
import com.lbs.crowd.entity.vo.AddressVO;
import com.lbs.crowd.entity.vo.OrderProjectVO;
import com.lbs.crowd.entity.vo.OrderVO;
import com.lbs.crowd.service.api.OrderService;
import com.stillcrowdfunding.until.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/26 16:37
 * @description
 **/
@RestController
public class OrderProjectController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/save/order/remote")
    public ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO){

        try {
            orderService.saveOrder(orderVO);

            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping(value = "/save/address/remote")
    public ResultEntity<String> saveAddressRemote(@RequestBody AddressVO addressVO){

        try {
            orderService.saveAddress(addressVO);

            return ResultEntity.successWithoutData();

        } catch (Exception e) {

            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

    }

    @RequestMapping(value = "/get/address/vo/remote")
    public ResultEntity<List<AddressVO>> getAddressVORemote(@RequestParam("memberId") Integer memberId){

        try {
            List<AddressVO> addressVOList = orderService.getAddressVOList(memberId);

            return ResultEntity.successWithoutData(addressVOList);

        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

    }

    @RequestMapping(value = "/get/order/project/vo/remote")
    public ResultEntity<OrderProjectVO> getOrderProjectVORemote(
            @RequestParam("projectId") Integer projectId,
            @RequestParam("returnId") Integer returnId){

        try {
            OrderProjectVO orderProjectVO = orderService.getOrderProjectVO(projectId,returnId);

            return ResultEntity.successWithoutData(orderProjectVO);
        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }
    }

}
