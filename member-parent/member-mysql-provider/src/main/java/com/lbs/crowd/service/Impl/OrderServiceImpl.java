package com.lbs.crowd.service.Impl;

import com.lbs.crowd.entity.po.AddressPO;
import com.lbs.crowd.entity.po.AddressPOExample;
import com.lbs.crowd.entity.po.OrderPO;
import com.lbs.crowd.entity.po.OrderProjectPO;
import com.lbs.crowd.entity.vo.AddressVO;
import com.lbs.crowd.entity.vo.OrderProjectVO;
import com.lbs.crowd.entity.vo.OrderVO;
import com.lbs.crowd.mapper.AddressPOMapper;
import com.lbs.crowd.mapper.OrderPOMapper;
import com.lbs.crowd.mapper.OrderProjectPOMapper;
import com.lbs.crowd.service.api.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/26 16:39
 * @description
 **/
@Transactional(readOnly = true)
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderProjectPOMapper orderProjectPOMapper;

    @Autowired
    private OrderPOMapper orderPOMapper;

    @Autowired
    private AddressPOMapper addressPOMapper;

    @Override
    public OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId) {

        return orderProjectPOMapper.selectOrderProjectVO(returnId);

    }

    @Override
    public List<AddressVO> getAddressVOList(Integer memberId) {

        AddressPOExample example = new AddressPOExample();

        example.createCriteria().andMemberIdEqualTo(memberId);

        List<AddressPO> addressPOList = addressPOMapper.selectByExample(example);

        List<AddressVO> addressVOList = new ArrayList<AddressVO>();

        for (AddressPO addressPO : addressPOList){

            AddressVO addressVO = new AddressVO();

            BeanUtils.copyProperties(addressPO,addressVO);

            addressVOList.add(addressVO);

        }

        return addressVOList;
    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    @Override
    public void saveAddress(AddressVO addressVO) {

        AddressPO addressPO = new AddressPO();

        BeanUtils.copyProperties(addressVO,addressPO);

        addressPOMapper.insert(addressPO);

    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    @Override
    public void saveOrder(OrderVO orderVO) {

        OrderPO orderPO = new OrderPO();

        BeanUtils.copyProperties(orderVO,orderPO);

        OrderProjectPO orderProjectPO = new OrderProjectPO();

        BeanUtils.copyProperties(orderVO.getOrderProjectVO(),orderProjectPO);

        // 保存OrderPO时自动生成的主键是orderProjectPO需要用的外键
        orderPOMapper.insert(orderPO);

        // 所以要从OrderPO对象中获取自动生成的主键orderId
        Integer id  = orderPO.getId();

        // 将orderId设置到OrderProjectPO当中
        orderProjectPOMapper.insert(orderProjectPO);

        orderProjectPO.setId(id);

    }
}
