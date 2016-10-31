package com.consumer.websockets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQEndPoint {

	static String hostname;
	static int port;
	static String username;
	static String password;
	static String virtualhost;

	//Find string match with exactly words
	private static boolean isContain(String string, String subString) {
		String pattern = "\\b" + subString + "\\b";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(string);
		return m.find();
	}

	//load rabbitmq server information (hostname , username, password,...)
	private static void loadConfig(){

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
						if (isContain(line,"hostname") == true) {
							String[] readHost = line.split("=");
							hostname = readHost[1];
						}
						if (isContain(line, "port") == true) {
							String[] readPort = line.split("=");
							port = Integer.parseInt(readPort[1]);
						}
						if (isContain(line, "username") == true) {
							String[] readUsername = line.split("=");
							username = readUsername[1];

						}
						if (isContain(line, "password") == true) {

							String[] readPassword = line.split("=");
							password = readPassword[1];

						}
						if (isContain(line, "virtualhost")) {
							String[] readRirtualhost = line.split("=");
							virtualhost = readRirtualhost[1];

						}
					}
				}
			} catch (IOException e) {
				System.out.println("Can't read line: " + e.getMessage());
			}
					
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("File didn't opened " + e.getMessage());
			}
		} catch (FileNotFoundException e) {
			System.out.println("File does't found " + e.getMessage());
		}

		
	}

	//Create connection to rabbitmq server
	public ConnectionFactory rabbitmqConnect() {
		ConnectionFactory factory = new ConnectionFactory();
		try {
			loadConfig();
			factory.setHost(hostname);
			factory.setPort(port);
			factory.setUsername(username);
			factory.setPassword(password);
			factory.setVirtualHost(virtualhost);
		} catch (Exception e) {
			System.out.println("Can't connect to rabbitmq server  " + (e.getMessage()));
		}
				
		return factory;
	}
	
	public static void main(String[] args) throws IOException {
		RabbitMQEndPoint rabbitend = new RabbitMQEndPoint();
		rabbitend.rabbitmqConnect();
		
		/*rabbitend.loadConfig();
		
		System.out.println("Hostname: " + rabbitend.hostname);
		System.out.println("Port: " + rabbitend.port);
		System.out.println("Username: " + rabbitend.username);
		System.out.println("Password: " + rabbitend.password);
		System.out.println("Virtualhost: " + rabbitend.virtualhost);
*/
	}

}
