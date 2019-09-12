/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.daoge.com
 */
package com.daoge.consumer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;


@Component
public class P2PConsumer {
	@Autowired
	private JavaMailSender javaMailSender;

	// 幂等性
	@JmsListener(destination = "${my_queue}")
	public void receive(String msg) throws Exception {
		if (StringUtils.isEmpty(msg)) {
			return;
		}
		// 1.解析json
		JSONObject parseObject = JSONObject.parseObject(msg);
		String userName = parseObject.getString("userName");
		String email = parseObject.getString("email");
		sendSimpleMail(email, userName);
		System.out.println("采用点对点模式，消费者成功获取到生产者的消息,msg:" + msg);

	}

	public void sendSimpleMail(String eamil, String userName) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		// 邮件来自 自己发自己
		message.setFrom(eamil);
		// 发送给谁
		message.setTo(eamil);
		// 邮件标题
		message.setSubject("ActiveMQ消息中间件");
		// 邮件内容
		message.setText("你好" + userName );
		// 发送邮件
		javaMailSender.send(message);
		System.out.println("邮件发送完成," + JSONObject.toJSONString(message));
	}

}
