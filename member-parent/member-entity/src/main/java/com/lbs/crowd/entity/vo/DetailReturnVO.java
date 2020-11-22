package com.lbs.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @date 2020/8/19 22:11
 * @description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailReturnVO {

    // 回报信息的主键
    private Integer returnId;

    // 支持金额
    private Integer supportMoney;

    // 是否限制单单笔购买数量，0表示不限购，1表示限购
    private Integer signalPurchase;

    // 如果单笔限购，那么具体的限购数量
    private Integer purchase;

    // 当前该档位支持者的数量
    private Integer supporterCount;

    // 运费，“0”为包邮
    private Integer freight;

    // 众筹成功后多少天后发货
    private Integer returnDate;

    // 回报内容介绍
    private String content;

}
