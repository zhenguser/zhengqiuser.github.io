/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.daoge.com
 */
package com.daoge.producer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;


@Component
public class P2pProducer {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Queue queue;

	// 每隔5秒种时间向队列中发送消息
	@Scheduled(fixedDelay = 5000)
	public void send() {
		String userName = System.currentTimeMillis() + "";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userName", userName);
		jsonObject.put("email", "yushengjun6442018@163.com");
		String msg = jsonObject.toJSONString();
		jmsMessagingTemplate.convertAndSend(queue, msg);
		System.out.println("采用点对点通讯模式,msg:" + msg);
	}
}
