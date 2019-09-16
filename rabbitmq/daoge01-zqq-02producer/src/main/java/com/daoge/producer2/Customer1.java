package com.daoge.producer2;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.daoge.MQConnectionUtils;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Customer1 {
	private static final String QUEUE_NAME = "test_queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		System.out.println("001");
		// 1.��ȡ����
		Connection newConnection = MQConnectionUtils.newConnection();
		// 2.��ȡͨ��
		final Channel channel = newConnection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);// ��֤һ��ֻ�ַ�һ�� ���Ʒ��͸�ͬһ�������� ���ó���һ����Ϣ
		DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String msgString = new String(body, "UTF-8");
				System.out.println("�����߻�ȡ��Ϣ:" + msgString);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {

				} finally {
					// �ֶ���ִ��Ϣ
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			}
		};
		// 3.��������
		channel.basicConsume(QUEUE_NAME, false, defaultConsumer);

	}

}
