package com.consumer.websockets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.consumer.websockets.RabbitMQEndPoint;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

@ServerEndpoint("/receive")
public class ConsumerWebSocket {
	
	String queuename;
	
	RabbitMQEndPoint endpoint = new RabbitMQEndPoint();
	ConnectionFactory fact;
	Connection connection;
	Channel channel;
	
	
	//Find string match with exactly words
			private static boolean isContain(String string, String subString) {
				String pattern = "\\b" + subString + "\\b";
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(string);
				return m.find();
			}

			//load rabbitmq server information (hostname , username, password,...)
			private void loadConfig(){

				String filename = "/apps/config/config.properties";

				File fin = new File(filename);
				FileInputStream fis;
				try {
					fis = new FileInputStream(fin);
					BufferedReader br = new BufferedReader(new InputStreamReader(fis));
					String line = null;
					try {
						while ((line = br.readLine()) != null) {
							if (!line.matches("^[#].*$")) {
								if (isContain(line,"queuename") == true) {
									String[] readHost = line.split("=");
									queuename = readHost[1];
								}
							}
						}
					} catch (IOException e) {
						System.out.println("Queue name does not exist or can't load config file: " + e.getMessage());
					}
							
					try {
						br.close();
					} catch (IOException e) {
						System.out.println("Config file didn't opened: " + e.getMessage());
					}
				} catch (FileNotFoundException e) {
					System.out.println("File doesn't exist: " + e.getMessage());
				}
			}
	
	

	@OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException{
		
		// Send the first message to the client
		session.getBasicRemote().sendText("This is the first server message");
		
		loadConfig();		
		fact = endpoint.rabbitmqConnect();
		try {
			connection = fact.newConnection();
			channel = connection.createChannel();
	        channel.queueDeclare(queuename, false, false, false, null);
	        QueueingConsumer consumer = new QueueingConsumer(channel);
	        channel.basicConsume(queuename, false, consumer);

	        while(true){
	            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	            if(session.isOpen()){
	            	message = new String(delivery.getBody());
	            	session.getBasicRemote().sendText(message);
	            }
	            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
	        }
		} catch (TimeoutException e) {
			System.out.println("Can't connect to rabbitmq server: " + e.getMessage());
		}
    }
	
	@OnOpen
    public void onOpen () {
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose () {
    	System.out.println("Connection closed");
    }
}
