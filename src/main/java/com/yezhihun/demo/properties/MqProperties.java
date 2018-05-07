package com.yezhihun.demo.properties;

/**
 * Created by tianye on 2018/5/4.
 */

//@Component
//@ConfigurationProperties(prefix = "rule")
//@PropertySource(value = "config/app.properties")
public class MqProperties {

    private String ruleQueue;

    private String ruleQueueKey;

    private String ruleExchange;

    public String getRuleQueue() {
        return ruleQueue;
    }

    public void setRuleQueue(String ruleQueue) {
        this.ruleQueue = ruleQueue;
    }

    public String getRuleQueueKey() {
        return ruleQueueKey;
    }

    public void setRuleQueueKey(String ruleQueueKey) {
        this.ruleQueueKey = ruleQueueKey;
    }

    public String getRuleExchange() {
        return ruleExchange;
    }

    public void setRuleExchange(String ruleExchange) {
        this.ruleExchange = ruleExchange;
    }
}
