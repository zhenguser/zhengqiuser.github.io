/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.daoge.com
 */
package com.daoge.demo02;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;


public class ProducerTest02 {
	// mq通讯地址
	private static String url = "tcp://127.0.0.1:61616";
	// 队列名称
	private static String topicName = "my_topic";

	public static void main(String[] args) throws JMSException {
		// 1.创建连接工厂 吗，密码采用默认密码admin 和admin
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
		// 2.创建连接
		Connection connection = factory.createConnection();
		// 3.创建会话 参数1 设置是否需要以事务方式提交 参数2 消息方式 采用自动签收
		connection.start();// 启动连接
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 4.创建目标(主题)
		Topic topic = session.createTopic(topicName);
		// 5.创建生产者
		MessageProducer producer = session.createProducer(topic);
		for (int i = 1; i <= 10; i++) {
			// 6.创建 消息
			TextMessage textMessage = session.createTextMessage("消息内容i:" + i);
			// 7.发送消息
			producer.send(textMessage);

		}
		// 8.关闭连接
		connection.close();
		System.out.println("消息发送完毕!");
	}
}
