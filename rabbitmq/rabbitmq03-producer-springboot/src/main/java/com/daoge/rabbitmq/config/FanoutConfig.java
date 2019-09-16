package com.daoge.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//发布订阅模式配置的交换机类型为fanout

//注入到spring容器中
@Component
public class FanoutConfig {

	// 邮件队列
	private String FANOUT_EMAIL_QUEUE = "fanout_eamil_queue";

	// 短信队列
	private String FANOUT_SMS_QUEUE = "fanout_sms_queue";
	// 交换机名称
	private String EXCHANGE_NAME = "fanoutExchange";

	// 1.定义邮件队列
	@Bean
	public Queue fanOutEamilQueue() {
		return new Queue(FANOUT_EMAIL_QUEUE);
	}

	@Bean
	public Queue fanOutSmsQueue() {
		return new Queue(FANOUT_SMS_QUEUE);
	}

	// 2.定义交换机
	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange(EXCHANGE_NAME);
	}

	// 3.邮件队列和交换机进行绑定
	@Bean
	Binding bindingExchangeEamil(Queue fanOutEamilQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(fanOutEamilQueue).to(fanoutExchange);
	}

	// 4.短信队列和交换机进行绑定
	@Bean
	Binding bindingExchangeSms(Queue fanOutSmsQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(fanOutSmsQueue).to(fanoutExchange);
	}
}
