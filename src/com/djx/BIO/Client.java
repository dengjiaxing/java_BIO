package com.djx.BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private static int DEFAULT_SERVER_PORT=12345;
	private static String DEFAULT_SERVER_IP="127.0.0.1";
	public static void  send(String expression) {
		send(DEFAULT_SERVER_PORT,expression);
	}
	public static void send(int port,String expression){
		System.out.println("�������ʽΪ��"+expression);
		Socket socket=null;
		BufferedReader in=null;
		PrintWriter out=null;
		try {
			socket=new Socket(DEFAULT_SERVER_IP, port);
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out=new PrintWriter(socket.getOutputStream(),true);
			out.println(expression);
			System.out.println("__���Ϊ��"+in.readLine());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    in=null;
			}
			if (out!=null) {
				out.close();
				out=null;
			}
			if (socket!=null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				socket=null;
				
			}
		}
	}
}
