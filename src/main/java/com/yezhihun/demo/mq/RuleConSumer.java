package com.yezhihun.demo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by tianye on 2018/5/3.
 */
@Component
@RabbitListener(queues = "rule.queue")
public class RuleConSumer {

    @RabbitHandler
    public void onMessage(String msg){
        System.out.println("receive msg :" + msg);
    }
}
