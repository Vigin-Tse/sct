package com.vg.sct.job.service.impl;

import com.vg.sct.common.support.exception.BusinessException;
import com.vg.sct.job.domain.TaskInfo;
import com.vg.sct.job.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author: xieweij
 * @time: 2021/8/4 14:08
 */
@Service
@Slf4j
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private Scheduler scheduler;

    /**
     * 获取任务分组名称
     * @return
     */
    @Override
    public List<String> getJobGroupNames() {
        try {
            return scheduler.getJobGroupNames();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 获取任务列表
     * @return
     */
    @Override
    public List<TaskInfo> getJobList() {
        List<TaskInfo> list = new ArrayList<>();
        try {
            for(String groupJob : this.getJobGroupNames()){

                for(JobKey jobKey : scheduler.getJobKeys(GroupMatcher.groupEquals(groupJob))){

                    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                    for(Trigger trigger : triggers){

                        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                        JobDetail jobDetail = scheduler.getJobDetail(jobKey);

                        String cronExpression = "";
                        Date createTime = null;
                        Long milliSeconds = 0L;
                        Integer repeatCount = 0;
                        Date startDate = null;
                        Date endDate = null;

                        if (trigger instanceof CronTrigger){

                            CronTrigger cronTrigger = (CronTrigger) trigger;
                            cronExpression = cronTrigger.getCronExpression();
                        } else if(trigger instanceof SimpleTrigger){

                            SimpleTrigger simpleTrigger = (SimpleTrigger) trigger;
                            milliSeconds = simpleTrigger.getRepeatInterval();
                            repeatCount = simpleTrigger.getRepeatCount();
                            startDate = simpleTrigger.getStartTime();
                            endDate = simpleTrigger.getEndTime();
                        }

                        TaskInfo info = new TaskInfo();
                        info.setData(jobDetail.getJobDataMap());
                        info.setJobName(jobKey.getName());
                        info.setJobGroupName(jobKey.getGroup());
                        info.setJobClassName(jobDetail.getJobClass().getName());
                        info.setJobDescription(jobDetail.getDescription());
                        info.setJobStatus(triggerState.name());
                        info.setCronExpression(cronExpression);
                        info.setCreateTime(createTime);
                        info.setRepeatCount(repeatCount);
                        info.setStartDate(startDate);
                        info.setMilliSeconds(milliSeconds);
                        info.setEndDate(endDate);
                        list.add(info);
                    }
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 添加简单任务
     * @param taskInfo
     */
    @Override
    public void addSimpleJob(TaskInfo taskInfo) {
        String jobName = taskInfo.getJobName();
        String jobClassName = taskInfo.getJobClassName();
        String jobGroupName = taskInfo.getJobGroupName();
        String jobDescription = taskInfo.getJobDescription();
        Date createTime = new Date();

        JobDataMap dataMap = new JobDataMap();
        if (taskInfo.getData() != null){
            dataMap.putAll(taskInfo.getData());
        }
        dataMap.put("createTime", createTime);

        try {

            if (this.checkExists(jobName, jobGroupName)){
                throw new BusinessException(String.format("任务已经存在, jobName:[%s],jobGroup:[%s]", jobName, jobGroupName));
            }

            //触发器的key值
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            //job的key值
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            //简单调度
            SimpleTrigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerKey)
                    .startAt(taskInfo.getStartDate())
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInMilliseconds(taskInfo.getMilliSeconds()) //设置间隔时间（毫秒）
                            .withRepeatCount(taskInfo.getRepeatCount()))//设置重复次数
                    .endAt(taskInfo.getEndDate())
                    .build();

            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(jobClassName);

            JobDetail jobDetail = JobBuilder
                    .newJob(clazz)
                    .withIdentity(jobKey)
                    .withDescription(jobDescription)
                    .usingJobData(dataMap)
                    .build();

            this.scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new BusinessException("任务添加失败");
        }
    }

    /**
     * 添加cron表达式任务
     * @param taskInfo
     */
    @Override
    public void addCronJob(TaskInfo taskInfo) {
        String jobName = taskInfo.getJobName();
        String jobClassName = taskInfo.getJobClassName();
        String jobGroupName = taskInfo.getJobGroupName();
        String jobDescription = taskInfo.getJobDescription();
        String cronExpression = taskInfo.getCronExpression();
        Date createTime = new Date();
        JobDataMap dataMap = new JobDataMap();
        if (taskInfo.getData() != null) {
            dataMap.putAll(taskInfo.getData());
        }
        dataMap.put("createTime", createTime);

        try {

            if(this.checkExists(jobName, jobGroupName)){
                throw new BusinessException(String.format("任务已经存在, jobName:[%s],jobGroup:[%s]", jobName, jobGroupName));
            }

            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                    .cronSchedule(cronExpression)
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder)
                    .build();
            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(jobClassName);

            JobDetail jobDetail = JobBuilder.newJob(clazz)
                    .withIdentity(jobKey)
                    .withDescription(jobDescription)
                    .usingJobData(dataMap)
                    .build();

            this.scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new BusinessException("任务添加失败");
        }
    }

    @Override
    public void editSimpleJob(TaskInfo info) {

    }

    @Override
    public void editCronJob(TaskInfo info) {

    }

    /**
     * 删除定时任务
     * @param jobName
     * @param jobGroup
     */
    @Override
    public void deleteJob(String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

        try {

            if (this.checkExists(jobName, jobGroup)){
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 暂停定时任务
     * @param jobName
     * @param jobGroup
     */
    @Override
    public void pauseJob(String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

        try {

            if (this.checkExists(jobName, jobGroup)){
                scheduler.pauseTrigger(triggerKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 恢复定时任务
     * @param jobName
     * @param jobGroup
     */
    @Override
    public void resumeJob(String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

        try {

            if (this.checkExists(jobName, jobGroup)){
                scheduler.resumeTrigger(triggerKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public boolean checkExists(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }
}
