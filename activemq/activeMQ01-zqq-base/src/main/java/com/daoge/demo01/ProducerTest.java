/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.daoge.com
 */
package com.daoge.demo01;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


public class ProducerTest {
	// mq通讯地址
	private static String url = "tcp://127.0.0.1:61616";
	// 队列名称
	private static String queueName = "my_queue";

	// 如果生产者以事务形式提交消息,消费者以事务形式接受消息
	// 第一次运行消费者 1.可以接受消息成功,但是没有标记为已消费
	// 第一次运行消费者 如果有生产者有新的消息继续发送,消费者接受每个消息都commit,标记为已经消费
	public static void main(String[] args) throws JMSException {
		// 1.创建连接工厂 吗，密码采用默认密码admin 和admin
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
		// 2.创建连接
		Connection connection = factory.createConnection();
		// 3.创建会话 参数1 设置是否需要以事务方式提交 参数2 消息方式 采用自动签收 true 表示以事务形式发送消息
		connection.start();// 启动连接
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		// 4.创建目标(队列)
		Queue queue = session.createQueue(queueName);
		// 5.创建生产者
		MessageProducer producer = session.createProducer(queue);
		// 设置消息是否 需要持久化 默认 DeliveryMode.NON_PERSISTENT
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);

		for (int i = 1; i <= 10; i++) {
			// 6.创建 消息
			TextMessage textMessage = session.createTextMessage("消息内容i:" + i);
			// 7.发送消息
			producer.send(textMessage);
			// 提交事务
			session.commit();
		}
		// 8.关闭连接
		connection.close();
		System.out.println("消息发送完毕!");
	}

}
