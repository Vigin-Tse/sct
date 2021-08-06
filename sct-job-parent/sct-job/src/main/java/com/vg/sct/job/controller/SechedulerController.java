package com.vg.sct.job.controller;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import com.vg.sct.common.support.http.PageInfo;
import com.vg.sct.job.domain.TaskInfo;
import com.vg.sct.job.domain.model.SchedulerJobLogsModel;
import com.vg.sct.job.executed.HttpExecuteJob;
import com.vg.sct.job.service.SchedulerJobLogService;
import com.vg.sct.job.service.SchedulerService;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: xieweij
 * @time: 2021/8/4 14:05
 */
@RestController
@RequestMapping("/job")
public class SechedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private SchedulerJobLogService logService;

    /**
     * 获取定时任务执行日志
     * @return
     */
    @GetMapping("/logs")
    public HttpResponse<List<SchedulerJobLogsModel>> getJobLogList(){
        PageInfo pageInfo = new PageInfo();
        List<SchedulerJobLogsModel> list = this.logService.findListPages(pageInfo);

        return HttpResponseConvert.success(list, pageInfo);
    }

    /**
     * 获取定时任务列表
     * @return
     */
    @GetMapping("/jobs")
    public HttpResponse<List<TaskInfo>> getJobList(){
        return HttpResponseConvert.success(this.schedulerService.getJobList());
    }

    /**
     * 添加cron定时任务
     * @param info
     * @return
     */
    @PostMapping("/add")
    public HttpResponse addJob(@RequestBody TaskInfo info){
        info.setJobClassName(HttpExecuteJob.class.getName());
        info.setJobGroupName(Scheduler.DEFAULT_GROUP);
        this.schedulerService.addCronJob(info);
        return HttpResponseConvert.success();
    }

    /**
     * 删除任务
     * @param jobName
     * @return
     */
    @GetMapping("/delete/{jobName}")
    public HttpResponse deleteJob(@PathVariable String jobName){
        this.schedulerService.deleteJob(jobName, Scheduler.DEFAULT_GROUP);
        return HttpResponseConvert.success();
    }
}
