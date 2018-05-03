package com.yezhihun.demo.mq;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tianye on 2018/5/3.
 */
@Component
public class RuleProducter {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Object msg){
        rabbitTemplate.convertAndSend("ruleQueue", JSONObject.toJSONString(msg));
    }
}
