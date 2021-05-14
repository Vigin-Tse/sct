package com.vg.sct.common.domain.model.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础模型类
 * @MappedSuperclass：
 * 1.@MappedSuperclass注解使用在父类（超类）上面，是用来标识父类的
 * 2.@MappedSuperclass标识的类表示其不能映射到数据库表，因为其不是一个完整的实体类，但是它所拥有的属性能够隐射在其子类对用的数据库表中
 * 3.@MappedSuperclass标识得嘞不能再有@Entity或@Table注解
 *
 * @EntityListeners：指定要用于实体或映射超类的回调侦听器类。
 *
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseModel implements Serializable {

    @CreatedBy
    @Column(name = "creator")
    private Integer creator;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedBy
    @Column(name = "modifier")
    private Integer modifier;

    @LastModifiedDate
    @Column(name = "modify_time")
    private Date modifyTime;

}
