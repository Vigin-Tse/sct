package com.vg.sct.job.service;

import com.vg.sct.job.domain.TaskInfo;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @author: xieweij
 * @time: 2021/8/4 14:07
 */
public interface SchedulerService {

    /**
     * 获取任务分组
     * @return
     */
    List<String> getJobGroupNames();

    /**
     * 获取任务列表
     * @return
     */
    List<TaskInfo> getJobList();

    /**
     * 添加简单任务
     * @param taskInfo
     */
    void addSimpleJob(TaskInfo taskInfo);

    /**
     * 添加cron表达式任务
     * @param taskInfo
     */
    void addCronJob(TaskInfo taskInfo);

    /**
     * 修改简单任务
     * @param info
     */
    void editSimpleJob(TaskInfo info);

    /**
     * 修改cron表达式任务
     * @param info
     */
    void editCronJob(TaskInfo info);

    /**
     * 删除定时任务
     * @param jobName
     * @param jobGroup
     */
    void deleteJob(String jobName, String jobGroup);

    /**
     * 暂停定时任务
     * @param jobName
     * @param jobGroup
     */
    void pauseJob(String jobName, String jobGroup);

    /**
     * 恢复定时任务
     * @param jobName
     * @param jobGroup
     */
    void resumeJob(String jobName, String jobGroup);

    /**
     * 验证任务是否存在
     * @param jobName
     * @param jobGroup
     * @return
     */
    boolean checkExists(String jobName, String jobGroup) throws SchedulerException;
}
