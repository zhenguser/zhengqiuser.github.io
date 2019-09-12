/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.daoge.com
 */
package com.daoge.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class ConfigQueue {
	@Value("${my_queue}")
	private String myQueue;

	// 1.首先需要将队列注入springboot容器中
	@Bean
	public Queue queue() {
		return new ActiveMQQueue(myQueue);
	}


}
