package cong.rabbitmq;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
//RPC
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Client {
	private Connection connection;
	private Channel channel;
	private String requestQueueName = "rpc_queue";
	private String replyQueueName;

	public static void main(String[] args) {
		Client connRpc = null;
		String response = null;
		System.out.println("Nhập 1 để kết nối! ^^!");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			connRpc = new Client();

			System.out.println(" [x] Requesting conn()");
			response = connRpc.call(reader.readLine().toString());
			System.out.println(" [.] Received: '" + response + "'");
		} catch (IOException | TimeoutException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (connRpc != null) {
				try {
					connRpc.close();
				} catch (IOException _ignore) {
				}
			}
		}
	}

	public Client() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		connection = factory.newConnection();
		channel = connection.createChannel();

		replyQueueName = channel.queueDeclare().getQueue();
	}

	public String call(String message) throws IOException, InterruptedException {
		final String corrId = UUID.randomUUID().toString();

		AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).build();

		channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

		final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);

		channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				if (properties.getCorrelationId().equals(corrId)) {
					response.offer(new String(body, "UTF-8"));
				}
			}
		});

		return response.take();
	}

	public void close() throws IOException {
		connection.close();
	}
}
