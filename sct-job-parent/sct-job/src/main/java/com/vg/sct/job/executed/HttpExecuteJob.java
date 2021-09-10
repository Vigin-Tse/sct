package com.vg.sct.job.executed;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieweij
 * @time: 2021/8/6 14:51
 */
@Slf4j
public class HttpExecuteJob implements Job {

    /**
     * 负载均衡
     */
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        String serviceId = dataMap.getString("serviceId");
        String method = dataMap.getString("method");
        method = StringUtils.isEmpty(method)? "POST" : method;
        String path = dataMap.getString("path");
        String contentType = dataMap.getString("contentType");
        contentType = StringUtils.isBlank(contentType) ? MediaType.APPLICATION_FORM_URLENCODED_VALUE : contentType;
        Map body = (Map) dataMap.get("body");

        //获取服务实例
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
        if (serviceInstance == null){
            throw new RuntimeException(String.format("%s服务暂不可用", serviceId));
        }

        //获取实际请求地址
        String url = String.format("http://%s/%s%s", serviceInstance.getServiceId(), serviceId, path);

        if (method.toUpperCase().equals("POST")){

            HttpHeaders headers = new HttpHeaders();//http请求头信息
            HttpMethod httpMethod = HttpMethod.resolve(method.toUpperCase());
            HttpEntity requestEntity = null;

            headers.setContentType(MediaType.parseMediaType(contentType));

            //json格式
            requestEntity = new HttpEntity(body, headers);

            log.info("Quartz定时调用==> url[{}] method[{}] data=[{}]", url, httpMethod, requestEntity);
            ResponseEntity result = restTemplate.exchange(url, httpMethod, requestEntity, String.class);
            log.info("Quartz定时调用结果==> url[{}] method[{}] result=[{}]", url, httpMethod, JSONObject.toJSON(result.getBody()));
        }else{
            Map<String, Object> params = new HashMap<>();
            if (body != null){
                params.putAll(body);
            }

            log.info("Quartz定时调用==> url[{}] method[{}] data=[{}]", url, method, JSONObject.toJSONString(params));
            ResponseEntity<String> result = restTemplate.getForEntity(url, String.class, params);
            log.info("Quartz定时调用结果==> url[{}] method[{}] result=[{}]", url, method, JSONObject.toJSON(result.getBody()));
        }
//        log.info("Quartz定时调用==> url[{}] method[{}] data=[{}]", url, httpMethod, requestEntity);
//        ResponseEntity result = restTemplate.exchange(url, httpMethod, requestEntity, String.class);
//        log.info("Quartz定时调用结果==> url[{}] method[{}] result=[{}]", url, httpMethod, JSONObject.toJSON(result.getBody()));
    }
}
