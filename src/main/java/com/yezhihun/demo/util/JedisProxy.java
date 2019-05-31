package com.yezhihun.demo.util;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisProxy {
	@Autowired
	private JedisPool jedisPool;

	public Jedis getJedis() {
		Enhancer enhancer = new Enhancer();
		// 设置代理的父类，就设置需要代理的类
		enhancer.setSuperclass(Jedis.class);
		// 设置自定义的代理方法
		Callback callback = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Jedis jedis = null;
				try {
					jedis = jedisPool.getResource();
					return method.invoke(jedis, args);
				} finally {
					if (jedis != null) {
						jedisPool.returnResource(jedis);
					}
				}
			}
		};
		enhancer.setCallback(callback);
		Object o = enhancer.create();
		Jedis jedis = null;
		if (o instanceof Jedis) {
			jedis = (Jedis) o;
			// jedis.auth(auth);
		}
		return jedis;
	}
}
