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
public class ConsumerSmsFanout {
	private static final String SMS_QUEUE = "sms_queue_fanout";
	// ����������
	private static final String DESTINATION_NAME = "my_fanout_estination";

	public static void main(String[] args) throws IOException, TimeoutException {
		System.out.println("��������������");
		// 1. ����mq����
		Connection connection = MQConnectionUtils.newConnection();
		// 2.����ͨ��
		Channel channel = connection.createChannel();
		// 3.������������
		channel.queueDeclare(SMS_QUEUE, false, false, false, null);
		// 4.�����߶��а󶨽�����
		channel.queueBind(SMS_QUEUE, DESTINATION_NAME, "");
		// 5.���Ѽ�����Ϣ
		DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("���������߻�ȡ������Ϣ:" + msg);
			}
		};
		channel.basicConsume(SMS_QUEUE, true, defaultConsumer);

	}

}
