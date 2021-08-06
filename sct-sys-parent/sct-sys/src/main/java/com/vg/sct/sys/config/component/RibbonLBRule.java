package com.vg.sct.sys.config.component;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author: xieweij
 * @time: 2021/6/9 14:34
 */
@Component
public class RibbonLBRule {

    /**
     * @bean 为全局（feignClient1、feignClient2...）配置,对所有 feignClient 的负载策略生效。
     * 如只针对某个 feignClient 只需在配置文件（yml）配置即可：
     * eureka-client-name:
     *   ribbon:
     *     NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
     *
     * 相关策略：
     *   随机：RandomRule
     *   轮询：RoundRobinRule
     *   重试：RetryRule
     *        在一个配置时问段内当选择 Server 不成功，则一直尝试选择一个可用的 Server
     *   响应时间权重：WeightedResponseTimeRule
     *   最空闲连接策略(最低并发)：BestAvailableRule
     *        逐个考察 Server，如果 Server 断路器打开，则忽略，再选择其中并发连接最低的 Server
     *   可用过滤策略：AvailabilityFilteringRule
     *        过滤掉一直连接失败并被标记为 circuit tripped 的 Server，过滤掉那些高并发连接的 Server（active connections 超过配置的网值）
     *   区域权衡策略：ZoneAvoidanceRule
     *        综合判断 Server 所在区域的性能和 Server 的可用性轮询选择 Server，并且判定一个 AWS Zone 的运行性能是否可用，剔除不可用的 Zone 中的所有 Server
     * @return
     */
    @Bean
    public IRule feignLBRule(){
        return new RandomRule();
    }
}
