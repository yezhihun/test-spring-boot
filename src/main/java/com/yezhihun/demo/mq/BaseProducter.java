package com.yezhihun.demo.mq;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

import javax.annotation.PostConstruct;

/**
 * Created by tianye on 2018/5/4.
 */
public abstract class BaseProducter<T> {

    private Logger log = LoggerFactory.getLogger(BaseProducter.class);

    protected String routingKey;

    protected String exchange;

    protected AmqpTemplate baseAmqpTemplate;

    @PostConstruct
    protected abstract void init();

    public <M extends T> void send(M m) throws Exception{
        baseAmqpTemplate.convertAndSend(exchange, routingKey, m);
        log.info("send:" + JSONObject.toJSONString(m));
    }

    public <M extends T> void sendDelay(M t, final Integer delayTime) throws Exception{

        baseAmqpTemplate.convertAndSend(exchange, routingKey, t, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(delayTime.toString());
                return message;
            }
        });
        log.info("send:" + JSONObject.toJSONString(t));
    }
}
