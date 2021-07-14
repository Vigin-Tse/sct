package com.vg.sct.account.domain.model;

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
@Table(name = "acc_seata_accout")
public class AccSeataAccountModel extends BaseModel{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "total")
    private Double total;

}
