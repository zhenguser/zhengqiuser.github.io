package com.daoge.fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.daoge.utils.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

// �ʼ�������
public class ConsumerEmailFanout {
	private static final String EMAIL_QUEUE = "email_queue_fanout";
	// ����������
	private static final String DESTINATION_NAME = "my_fanout_estination";

	public static void main(String[] args) throws IOException, TimeoutException {
		System.out.println("�ʼ���������");
		// 1. ����mq����
		Connection connection = MQConnectionUtils.newConnection();
		// 2.����ͨ��
		Channel channel = connection.createChannel();
		// 3.������������
		channel.queueDeclare(EMAIL_QUEUE, false, false, false, null);
		// 4.�����߶��а󶨽�����
		channel.queueBind(EMAIL_QUEUE, DESTINATION_NAME, "");
		// 5.���Ѽ�����Ϣ
		DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("�ʼ������߻�ȡ������Ϣ:" + msg);
			}
		};
		channel.basicConsume(EMAIL_QUEUE, true, defaultConsumer);

	}

}
