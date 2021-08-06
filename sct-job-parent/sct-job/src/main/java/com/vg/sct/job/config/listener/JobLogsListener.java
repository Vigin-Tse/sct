package com.vg.sct.job.config.listener;

import com.alibaba.fastjson.JSONObject;
import com.vg.sct.job.domain.model.SchedulerJobLogsModel;
import com.vg.sct.job.service.SchedulerJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.*;

import java.util.Date;

/**
 * 任务调度监听器
 * @author: xieweij
 * @time: 2021/8/5 9:45
 */
@Slf4j
public class JobLogsListener implements JobListener {

    private SchedulerJobLogService logService;

    public JobLogsListener(SchedulerJobLogService logService){
        this.logService = logService;
    }


    @Override
    public String getName() {
        return "JobLogsListener";
    }

    /**
     * 执行前调度
     * @param jobExecutionContext
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    /**
     * 调度完成 或 异常时执行
     * @param jobExecutionContext
     * @param e
     */
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        //获取任务详情
        JobDetail detail = jobExecutionContext.getJobDetail();
        JobDataMap dataMap = detail.getJobDataMap(); //任务执行传入的数据

        String jobName = detail.getKey().getName();
        String jobGroup = detail.getKey().getGroup();
        String alarmMail = dataMap.getString("alarmMail");
        String jobClass = detail.getJobClass().getName();
        String description = detail.getDescription();
        String exception = null;
        String cronExpression = null;
        Integer status = 1;

        //获取触发器
        Trigger trigger = jobExecutionContext.getTrigger();
        String triggerClass = trigger.getClass().getName();
        if(trigger instanceof CronTrigger){
            CronTrigger cronTrigger = (CronTrigger) trigger;
            cronExpression = cronTrigger.getCronExpression();
        }

        //如果任务执行过程发生异常
        if (e != null){
            status = 0;
            exception = e.getMessage();

            //如果（管理员）邮箱地址不是空，则发邮件通知管理员
            if(!StringUtils.isEmpty(alarmMail)){
                String title = String.format("[%s]任务执行异常-%s", jobName, DateFormatUtils.format(new Date(), DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()));
                log.error("send email log:{}", title);
                try {
                    log.info("执行异常，通知管理员！");
                } catch (Exception em) {
                    log.error("==> send alarmMail error:{}", em);
                }
            }
        }

        //保存任务运行日志
        SchedulerJobLogsModel logModel = new SchedulerJobLogsModel();
        logModel.setJobName(jobName);
        logModel.setJobGroup(jobGroup);
        logModel.setJobClass(jobClass);
        logModel.setJobDescription(description);
        logModel.setRunTime(jobExecutionContext.getJobRunTime());
        logModel.setCreateTime(new Date());
        logModel.setCronExpression(cronExpression);
        logModel.setStartTime(jobExecutionContext.getFireTime());
        logModel.setTriggerClass(triggerClass);
        logModel.setEndTime(new Date(jobExecutionContext.getFireTime().getTime() + jobExecutionContext.getJobRunTime()));
        logModel.setJobData(JSONObject.toJSONString(dataMap));
        logModel.setException(exception);
        logModel.setStatus(status);
        logService.addLog(logModel);
    }

}
