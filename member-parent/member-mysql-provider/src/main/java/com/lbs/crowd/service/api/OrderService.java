package com.lbs.crowd.service.api;

import com.lbs.crowd.entity.vo.AddressVO;
import com.lbs.crowd.entity.vo.OrderProjectVO;
import com.lbs.crowd.entity.vo.OrderVO;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/26 16:39
 * @description
 **/
public interface OrderService {

    OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId);

    List<AddressVO> getAddressVOList(Integer memberId);

    void saveAddress(AddressVO addressVO);

    void saveOrder(OrderVO orderVO);
}
