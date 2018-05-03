package com.yezhihun.demo.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tianye on 2018/5/3.
 */
@Configuration
public class RabbitMqConfiguration {


    @Bean
    TopicExchange ruleExchange(){
        return new TopicExchange("rule.exchange");
    }

    @Bean
    public Queue ruleQueue(){
        return new Queue("rule.queue");
    }

    @Bean
    Binding bindingRuleExchangeMessage(Queue ruleQueue, TopicExchange ruleExchange){
        return BindingBuilder.bind(ruleQueue).to(ruleExchange).with("rule.queue.key");
    }


}
