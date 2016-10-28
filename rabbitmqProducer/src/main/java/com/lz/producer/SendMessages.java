package com.lz.producer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@Service("sendService")
public class SendMessages{
	
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
		private void loadConfig() throws IOException {

			String filename = "/apps/config/config.properties";

			File fin = new File(filename);
			FileInputStream fis = new FileInputStream(fin);

			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			

			String line = null;
			while ((line = br.readLine()) != null) {
				if (!line.matches("^[#].*$")) {
					if (isContain(line,"queuename") == true) {
						String[] readHost = line.split("=");
						queuename = readHost[1];
					}
					
				}
			}
					
			br.close();
		}
	
	public void sendMessage(String mesg) throws IOException{
		
		fact = endpoint.rabbitmqConnect();
		connection = fact.newConnection();
		channel = connection.createChannel();
		
		loadConfig();
		
		channel.queueDeclare(queuename, false, false, false, null);
		channel.basicPublish("", queuename, null, mesg.getBytes());
		
		System.out.println("Messages " + mesg + " sent");
	}
	
	
		
	public static void main(String[] args) {
		SendMessages send = new SendMessages();
		try {
			send.sendMessage("Hell");
			//send.receiveMessage();
		} catch (IOException e) {
			System.out.println("Please check your queue server");
			e.printStackTrace();
		}
	}

}