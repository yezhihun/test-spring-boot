//package com.yezhihun.demo.mq;
//
//import com.alibaba.fastjson.JSONObject;
//import com.rabbitmq.client.Channel;
//import com.yezhihun.demo.entity.BaseTable;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.*;
//import org.springframework.stereotype.Component;
//
///**
// * Created by tianye on 2018/5/3.
// */
//@Component
//@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${rule.queue}", autoDelete = "false", durable = "true")
//                                        , exchange = @Exchange(type = "topic", durable = "true", value = "${rule.exchange}")
//                                        , key = "${rule.queue.key}"))
//public class RuleConSumer extends BaseConsumer<BaseTable>{
//
//    private Logger log = LoggerFactory.getLogger(RuleConSumer.class);
//
//    @RabbitHandler
//    @Override
//    public void onMessage(Channel channel, Message message, BaseTable t) throws Exception {
//        log.info(JSONObject.toJSONString(t));
//        ackForSuccess(channel, message, message.getMessageProperties().getConsumerTag());
////        unAck(channel, message, message.getMessageProperties().getConsumerTag());
//    }
//
//    @Override
//    public void onError(Message message, Channel channel, Object... objs) throws Exception {
//
//    }
//
//}
