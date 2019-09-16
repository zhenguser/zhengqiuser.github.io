package com.daoge;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MQConnectionUtils {

	public static Connection newConnection() throws IOException, TimeoutException {
		// 1.�������ӹ���
		ConnectionFactory factory = new ConnectionFactory();
		// 2.���÷�������ַ
		factory.setHost("127.0.0.1");
		// 3.����Э��˿ں�
		factory.setPort(5672);
		// 4.����virture host
		factory.setVirtualHost("/test001_host");
		// 5.�����û�����
		factory.setUsername("test001");
		// 6.�����û�����
		factory.setPassword("123456");
		// 7.�����µ�����
		Connection newConnection = factory.newConnection();
		return newConnection;
	}

}
