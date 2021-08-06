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
        Map<String, Object> body = (Map<String, Object>) dataMap.get("body");

        //获取服务实例
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
        if (serviceInstance == null){
            throw new RuntimeException(String.format("%s服务暂不可用", serviceId));
        }

        //获取实际请求地址
        String url = String.format("%s/%s%s", serviceInstance.getUri(), serviceId, path);

        HttpHeaders headers = new HttpHeaders();//http请求头信息
        HttpMethod httpMethod = HttpMethod.resolve(method.toUpperCase());
        HttpEntity requestEntity = null;

        headers.setContentType(MediaType.parseMediaType(contentType));
        if (contentType.contains(MediaType.APPLICATION_JSON_VALUE)){
            //json格式
            requestEntity = new HttpEntity(body, headers);
        }else{
            // 表单形式
            // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
            MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
            if (body != null){
                Map data = JSONObject.parseObject(JSONObject.toJSONString(body), Map.class);
                params.putAll(params);
                requestEntity = new HttpEntity(params, headers);
            }
        }
        log.info("Quartz定时调用==> url[{}] method[{}] data=[{}]", url, httpMethod, requestEntity);
        ResponseEntity result = restTemplate.exchange(url, httpMethod, requestEntity, String.class);
        log.info("Quartz定时调用结果==> url[{}] method[{}] result=[{}]", url, httpMethod, JSONObject.toJSON(result.getBody()));
    }
}
