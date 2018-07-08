package com.imooc.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imooc.order.utils.JsonUtil;
import com.imooc.product.common.ProductInfoOutput;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductInfoReceiver {
    private static final String PRODUCT_STOCK_TEMPLATE="product_stock_%s";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message){
        //message转成productInfoOutput对象
        List<ProductInfoOutput> productInfoOutputList= (List<ProductInfoOutput>) JsonUtil.fromJson(message,
                new TypeReference<List<ProductInfoOutput>>() {});
        System.out.println("从productInfo中接收消息"+productInfoOutputList);
        //存储到redis中
        for (ProductInfoOutput productInfoOutput:productInfoOutputList){
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE,
                    productInfoOutput.getProductId()),
                    String.valueOf(productInfoOutput.getProductStock()));
        }
    }
}
