package com.vg.sct.order.domain.model;

import com.vg.sct.common.domain.model.base.BaseModel;
import lombok.Data;

import javax.persistence.*;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 16:15
 */
@Data
@Entity
@Table(name = "biz_seata_order")
public class BizSeataOrderModel extends BaseModel{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "product_id")
    private Integer productId;

    /**
     * 数量
     */
    @Column(name = "count")
    private Integer count;

    /**
     * 订单(总)价格
     */
    @Column(name = "money")
    private Double money;

}
