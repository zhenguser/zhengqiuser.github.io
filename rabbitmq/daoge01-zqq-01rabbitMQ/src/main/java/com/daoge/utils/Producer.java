package com.daoge.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

// �򵥶���������
public class Producer {

	// ��������
	private static final String QUEUE_NAME = "yushengjun_644064779";

	public static void main(String[] args) throws IOException, TimeoutException {

		// 1.����һ���µ�����
		Connection connection = MQConnectionUtils.newConnection();
		// 2.����ͨ��
		Channel channel = connection.createChannel();
		// 3.����һ������
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);
		for (int i = 1; i <= 50; i++) {
			// 4.����msg
			String msg = "yushengjun_msg_" + i;
			// System.out.println("������Ͷ����Ϣ����:" + msg);
			// 5.�����߷�����Ϣ��
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		}

		// �ر�ͨ��������
		channel.close();
		connection.close();
	}

}
