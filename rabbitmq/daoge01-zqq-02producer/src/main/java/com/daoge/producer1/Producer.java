package com.daoge.producer1;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.daoge.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
	private static final String QUEUE_NAME = "test_queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 1.��ȡ����
		Connection newConnection = MQConnectionUtils.newConnection();
		// 2.����ͨ��
		Channel channel = newConnection.createChannel();
		// 3.������������
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String msg = "test_yushengjun110";
		System.out.println("�����߷�����Ϣ:" + msg);
		// 4.������Ϣ
		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		channel.close();
		newConnection.close();
	}

}
