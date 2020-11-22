package com.lbs.crowd.service.api;

import com.lbs.crowd.entity.po.MemberPO;

/**
 * @author Administrator
 * @date 2020/8/6 16:51
 * @description
 **/
public interface MemberService {

    MemberPO getMemberPOByLoginAcct(String loginacct);

    void saveMember(MemberPO memberPO);
}
