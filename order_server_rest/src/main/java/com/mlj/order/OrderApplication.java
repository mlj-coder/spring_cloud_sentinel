package com.mlj.order;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.mlj.order.exception.ExceptionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan("com.mlj.order.entity")
public class OrderApplication {

    /**
     * sentinel支持对restTemplate的支持，在构造restTemplate的时候，添加@SentinelRestTemplate
     *
     *      资源名
     *          httpmethod:schema://host:port/path :协议、主机、端口和路径
     *          httpmethod:schema://host:port :协议、主机和端口
     *      @SentinelRestTemplate
     *          fallback：降级执行的静态方法
     *          fallbackclass：降级处理执行的方法所在的类
     *          blockHandler：限流执行的静态方法
     *          blockHandlerClass：限流处理执行的方法所在的类
     */
    @SentinelRestTemplate(fallback = "handleFallback",fallbackClass = ExceptionUtils.class, blockHandler="handleBlock", blockHandlerClass= ExceptionUtils.class)
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
