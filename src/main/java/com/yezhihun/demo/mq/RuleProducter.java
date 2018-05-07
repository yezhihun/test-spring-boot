package com.yezhihun.demo.mq;

import com.yezhihun.demo.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by tianye on 2018/5/3.
 */
@Component
public class RuleProducter extends BaseProducter<User>{


    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Value("${rule.exchange}")
    private String ruleExchange;
    @Value("${rule.queue.key}")
    private String ruleQueueKey;

    @Override
    protected void init() {
        super.routingKey = ruleQueueKey;
        super.exchange = ruleExchange;
        super.baseAmqpTemplate = rabbitTemplate;
    }

}
