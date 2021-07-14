package com.vg.sct.product.domain.model;

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
@Table(name = "pro_seata_storage")
public class ProSeataStorageModel extends BaseModel{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id")
    private Integer productId;

    /**
     * 剩余库存
     */
    @Column(name = "residue")
    private Double residue;

}
