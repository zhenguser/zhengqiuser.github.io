package com.daoge.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MQConnectionUtils {

	// �����µ�MQ����
	public static Connection newConnection() throws IOException, TimeoutException {
		// 1.�������ӹ���
		ConnectionFactory factory = new ConnectionFactory();
		// 2.�������ӵ�ַ
		factory.setHost("127.0.0.1");
		// 3.�����û�����
		factory.setUsername("admin_yushengjun");
		// 4.�����û�����
		factory.setPassword("123456");
		// 5.����amqpЭ��˿ں�
		factory.setPort(5672);
		// 6.����VirtualHost��ַ
		factory.setVirtualHost("/admin_host");
		Connection connection = factory.newConnection();
		return connection;
	}

}
