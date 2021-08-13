package com.vg.sct.job.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieweij
 * @time: 2021/8/13 9:51
 */
@RestController
@RequestMapping("/rt")
@Slf4j
public class RestTemplateDemo {

    /**
     * 负载均衡
     */
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/sys/post")
    public void sysPost(){

        //获取服务实例
        ServiceInstance serviceInstance = loadBalancerClient.choose("sct-sys");
        //获取实际请求地址
        String url = String.format("http://%s/%s%s", serviceInstance.getServiceId(), "sct-sys", "/job/post");

        Map<String, Object> body = new HashMap<>();
        body.put("name", "丽丽");
        body.put("age", 26);

        HttpHeaders headers = new HttpHeaders();//http请求头信息
        HttpMethod httpMethod = HttpMethod.resolve("POST".toUpperCase());
        HttpEntity requestEntity = null;

        headers.setContentType(MediaType.parseMediaType("application/json"));
        requestEntity = new HttpEntity(body, headers);

        log.info("url[{}] method[{}] data=[{}]", url, httpMethod, requestEntity);
        ResponseEntity result = restTemplate.exchange(url, httpMethod, requestEntity, String.class);
        log.info("url[{}] method[{}] result=[{}]", url, httpMethod, JSONObject.toJSON(result.getBody()
        ));
    }

    @GetMapping("/sys/get")
    public void sysGet(){
//        String result = restTemplate.getForObject("http://sct-sys/sct-sys/job/cget", String.class);
//        String result = restTemplate.getForObject("http://10.10.22.38:5002/sct-sys/job/cget", String.class);

        Map<String, Object> params = new HashMap<>();
        params.put("name", "丽丽");
        params.put("age", "35");

        restTemplate.getForEntity("http://sct-sys/sct-sys/job/get?name={name}&age={age}", String.class,params);
    }


    @GetMapping("/sys/get2")
    public void sysGet2(){

        //获取服务实例
        ServiceInstance serviceInstance = loadBalancerClient.choose("sct-sys");
        //获取实际请求地址
        String url = String.format("http://%s/%s%s", serviceInstance.getServiceId(), "sct-sys", "/job/get?name={name}&age={age}");

        Map<String, Object> body = new HashMap<>();
        body.put("name", "丽丽");
        body.put("age", 26);

        HttpHeaders headers = new HttpHeaders();//http请求头信息
        HttpMethod httpMethod = HttpMethod.resolve("GET".toUpperCase());
        HttpEntity requestEntity = null;

//        headers.setContentType(MediaType.parseMediaType("application/json"));
        headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded"));
        requestEntity = new HttpEntity(null, headers);

        log.info("url[{}] method[{}] data=[{}]", url, httpMethod, requestEntity);
        ResponseEntity result = restTemplate.exchange(url, httpMethod, requestEntity, String.class, body);
        log.info("url[{}] method[{}] result=[{}]", url, httpMethod, JSONObject.toJSON(result.getBody()
        ));
    }


    @GetMapping("/sys/cget")
    public void sysCGet(){
//        String result = restTemplate.getForObject("http://sct-sys/sct-sys/job/cget", String.class);
        String result = restTemplate.getForObject("http://10.10.22.38:5002/sct-sys/job/cget", String.class);
    }
}
