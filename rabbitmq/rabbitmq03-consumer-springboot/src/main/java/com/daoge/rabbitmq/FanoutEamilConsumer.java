package com.daoge.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

//�ʼ�����
@Component
//��������
@RabbitListener(queues = "fanout_eamil_queue")
public class FanoutEamilConsumer {


	@RabbitHandler
	public void process(String msg) throws Exception {
		System.out.println("�ʼ������߻�ȡ��������Ϣmsg:" + msg);
	}
}
