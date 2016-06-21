package com.javamas.araignee.mq;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

/**
 * RabbitMQとの接続を保持するクラス.
 * @author nayuta
 */
public class MessageQueueManager {

	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Channel channel = null;
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * RabbitMQにメッセージを送信する.
	 * メッセージのフォーマットはJSON.
	 * @param object
	 * @param queueName
	 */
	public void sendMessage(Object object, String queueName) {
		try {
			connectMessageQueue();
			setQueue(queueName);
			String json = mapper.writeValueAsString(object);
			channel.basicPublish("", queueName, null, json.getBytes());
		} catch (JsonProcessingException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * RabbitMQからメッセージを受信する.
	 * メッセージのパースに失敗した場合、nullを返す.
	 * @param dto
	 * @param queueName
	 * @throws IOException
	 */
	public <T> T recieveMessage(Class<T> dto, String queueName) throws IOException {
		try {
			connectMessageQueue();
			setQueue(queueName);
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName, true, consumer);
			Delivery delivery = consumer.nextDelivery();
			return mapper.readValue(new String(delivery.getBody()), dto);
		} catch (InterruptedException ex) {
			return null;
		} catch (IOException ex) {
			return null;
		}
	}

	/**
	 * RabbitMQとの接続を確立する.
	 * 接続情報はシステムプロパティから取得する.
	 * @throws IOException
	 */
	private void connectMessageQueue() {
		try {
			factory = new ConnectionFactory();
			factory.setUsername(System.getProperty("user"));
			factory.setPassword(System.getProperty("password"));
			factory.setHost(System.getProperty("host"));
			factory.setVirtualHost(System.getProperty("vhost"));
			connection = factory.newConnection();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * メッセージを送受信するQueueを設定する.
	 * @param queueName
	 * @throws IOException
	 */
	private void setQueue(String queueName) {
		try {
			channel = connection.createChannel();
			channel.queueDeclare(queueName, false, false, false, null);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}