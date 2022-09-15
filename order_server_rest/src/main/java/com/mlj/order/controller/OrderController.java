package com.mlj.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.mlj.order.feign.ProductFeignClient;
import com.mlj.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {


    //注入rest Template对象
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @SentinelResource
     *      blockHandler：声明熔断时调用的降级方法
     *      fallback：声明抛出异常调用的降级方法
     *      value：自定义的资源名称
     *          * 不设置就显示：当前全类名.方法名
     */
    //@SentinelResource(value = "order", blockHandler = "orderblockHandler", fallback = "orderfallback")
    @RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id){
        if(id != 1){
            throw new RuntimeException("错误");
        }
        Product forObject = restTemplate.getForObject("http://service-product/product/"+id, Product.class);
        return forObject;
    }


//    /**
//     * 熔断执行的降级方法
//     */
//    public Product orderblockHandler(Long id) {
//        Product product = new Product();
//        product.setProductName("触发熔断降级方法");
//        return product;
//    }
//    /**
//     * 异常执行的降级方法
//     */
//    public Product orderfallback(Long id) {
//        Product product = new Product();
//        product.setProductName("触发抛出异常方法");
//        return product;
//    }


}
