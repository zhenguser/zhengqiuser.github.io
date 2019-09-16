package com.daoge.routing;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.daoge.utils.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

// ������  ���������� producerfanout����
public class RoutingProducer {

	// ����������
	private static final String DESTINATION_NAME = "my_routing_estination";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 1. ����mq����
		Connection connection = MQConnectionUtils.newConnection();
		// 2.����ͨ��
		Channel channel = connection.createChannel();
		// 3.�����߰󶨽����� ����1 ���������� ����2 ����������
		channel.exchangeDeclare(DESTINATION_NAME, "direct");
		String routingKey = "yushengjun";
		// 4.������Ϣ
		String msg = "my_routing_destination_msg" + routingKey;
		System.out.println("������Ͷ����Ϣ:" + msg);
		// 5.������Ϣ my_fanout_estination routingKey
		channel.basicPublish(DESTINATION_NAME, routingKey, null, msg.getBytes());
		// 6.�ر�ͨ�� ������
		channel.close();
		connection.close();

	}

}
