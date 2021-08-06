package com.vg.sct.job.config;

import com.vg.sct.job.config.listener.JobLogsListener;
import com.vg.sct.job.service.SchedulerJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.client.RestTemplate;

/**
 * @author: xieweij
 * @time: 2021/8/4 14:14
 */
@Configuration
@Slf4j
public class QuartzSchedulerConfig implements SchedulerFactoryBeanCustomizer {

    @Autowired
    private JobLogsListener jobLogsListener;

    @Bean
    public JobLogsListener jobLogsListener(SchedulerJobLogService schedulerJobLogService){
        return new JobLogsListener(schedulerJobLogService);
    }

    @Bean
    @LoadBalanced//开启负载均衡的功能
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
        schedulerFactoryBean.setStartupDelay(5); //延时5秒启动
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setGlobalJobListeners(jobLogsListener); // 任务执行日志监听
    }
}
