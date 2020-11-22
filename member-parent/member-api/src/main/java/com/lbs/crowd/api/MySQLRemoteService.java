package com.lbs.crowd.api;

import com.lbs.crowd.entity.po.MemberPO;
import com.lbs.crowd.entity.vo.*;
import com.stillcrowdfunding.until.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/6 16:33
 * @description
 **/
@FeignClient("lbs-crowd-mysql")
public interface MySQLRemoteService {

    @RequestMapping(value = "/save/memberpo/remote")
    public ResultEntity<String> saveMember(@RequestBody MemberPO memberPO);

    @RequestMapping(value = "get/memberpo/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct);

    @RequestMapping(value = "save/project/vo/remote")
    ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId);

    @RequestMapping(value = "/get/portal/type/project/data/remote")
    public ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote();

    @RequestMapping(value = "/get/project/detail/remote/{projectId}")
    public ResultEntity<DetailProjectVO> getProjectDetailRemote(@PathVariable("projectId") Integer projectId);

    @RequestMapping(value = "/get/order/project/vo/remote")
    public ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("projectId") Integer projectId, @RequestParam("returnId") Integer returnId);

    @RequestMapping(value = "/get/address/vo/remote")
    public ResultEntity<List<AddressVO>> getAddressVORemote(@RequestParam("memberId") Integer memberId);

    @RequestMapping(value = "/save/address/remote")
    public ResultEntity<String> saveAddressRemote(@RequestBody AddressVO addressVO);

    @RequestMapping(value = "/save/order/remote")
    public ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO);
}


