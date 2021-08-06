package com.vg.sct.job.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 任务详情
 * @author: xieweij
 * @time: 2021/8/4 14:02
 */
@Getter
@Setter
public class TaskInfo  implements Serializable {

    private static final long serialVersionUID = -7693857859775581311L;
    /**
     * 增加或修改标识
     */
    private int id;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务描述
     */
    private String jobDescription;
    /**
     * 任务类名
     */
    private String jobClassName;

    /**
     * 任务分组
     */
    private String jobGroupName;

    /**
     * 任务状态
     */
    private String jobStatus;

    /**
     * 任务类型 SimpleTrigger-简单任务,CronTrigger-表达式
     */
    private String jobTrigger;

    /**
     * 任务表达式
     */
    private String cronExpression;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 间隔时间（毫秒）
     */
    private Long milliSeconds;

    /**
     * 重复次数
     */
    private Integer repeatCount;

    /**
     * 起始时间
     */
    private Date startDate;

    /**
     * 终止时间
     */
    private Date endDate;

    /**
     * 执行数据
     */
    private Map data;

}