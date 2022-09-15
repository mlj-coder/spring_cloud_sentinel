package com.mlj.order.exception;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.mlj.product.entity.Product;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * 静态方法
 *      返回值：SentinelClientHttpResponse
 *      参数：request，byte[]，clientRquestExcetion，blockException
 */
public class ExceptionUtils {

    //限流熔断业务逻辑
    public static SentinelClientHttpResponse handleBlock(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        Product product = new Product();
        product.setProductName("限流熔断降级");
        return new SentinelClientHttpResponse(JSON.toJSONString(product));
    }

    public static SentinelClientHttpResponse handleFallback(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
        Product product = new Product();
        product.setProductName("异常熔断降级");
        return new SentinelClientHttpResponse(JSON.toJSONString(product));
    }
}
