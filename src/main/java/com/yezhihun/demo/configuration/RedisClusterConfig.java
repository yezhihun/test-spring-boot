package com.yezhihun.demo.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置
 */
@Configuration
@ConditionalOnClass({ JedisCluster.class })
public class RedisClusterConfig {
	// 超时时间
	@Value("${redis.timeout}")
	private int timeout;
	// 集群模式ip端口列表
	@Value("${redis.cluster.nodes}")
	private String clusterNodes;
	@Value("${redis.jedis.pool.max-idle}")
	private int maxIdle;
	@Value("${redis.jedis.pool.max-wait}")
	private long maxWaitMillis;
	@Value("${redis.commandTimeout}")
	private int commandTimeout;
	// @Value("${redis.password}")
	// private String password;

	// 集群模式打开
	// @Bean
	public JedisCluster getJedisCluster() {
		String[] cNodes = clusterNodes.split(",");
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		// 分割出集群点
		for (String cNode : cNodes) {
			String[] hp = cNode.split(":");
			nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
		}
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		// 生产环境使用
		JedisCluster jedisCluster = new JedisCluster(nodes, commandTimeout, timeout, maxIdle, null, jedisPoolConfig);
		return jedisCluster;
	}
}
