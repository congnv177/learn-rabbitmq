package cong.learn;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;

public class Send {
	private final static String QUEUE_NAME = "hello";
	public static void main(String[] args) throws java.io.IOException, TimeoutException {
		//create connect to the server
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		//declared a queue
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println("Nhap message can gui di:");
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		String message = bufferReader.readLine();
//		String message = "hello world";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		
		channel.close();
		connection.close();
	}
}
