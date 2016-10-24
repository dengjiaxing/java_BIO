package com.djx.BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.corba.se.spi.activation.ServerHolder;

public class ServerNormal {
	private static int DEFAULT_PORT=12345;
	private static ServerSocket server;
	public static void start() throws IOException{
		start(DEFAULT_PORT);
	}
	public synchronized static void start(int port) throws IOException {
		if (server!=null) return;
		try {
			server =new ServerSocket(port);
			System.out.println("服务器已启动，端口号："+port);
			Socket socket;
			while(true){
				socket=server.accept();
				new Thread(new ServerHandler(socket)).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("服务器关闭");
			server.close();
			server=null;
		}
	}
}
