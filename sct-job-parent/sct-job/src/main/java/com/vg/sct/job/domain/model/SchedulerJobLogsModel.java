package com.vg.sct.job.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: xieweij
 * @time: 2021/8/4 11:29
 */
@Data
@Entity
@Table(name = "scheduler_job_logs")
public class SchedulerJobLogsModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    /**
     * 任务名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 任务组名
     */
    @Column(name = "job_group")
    private String jobGroup;

    /**
     * 任务执行类
     */
    @Column(name = "job_class")
    private String jobClass;

    /**
     * 任务描述
     */
    @Column(name = "job_description")
    private String jobDescription;

    /**
     * 任务触发器
     */
    @Column(name = "trigger_class")
    private String triggerClass;

    /**
     * 任务表达式
     */
    @Column(name = "cron_expression")
    private String cronExpression;

    /**
     * 运行时间
     */
    @Column(name = "run_time")
    private Long runTime;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 日志创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 任务执行数据
     */
    @Column(name = "job_data")
    private String jobData;

    /**
     * 异常
     */
    @Column(name = "exception")
    private String exception;

    /**
     * 状态：0-失败 1-成功
     */
    @Column(name = "status")
    private Integer status;
}
