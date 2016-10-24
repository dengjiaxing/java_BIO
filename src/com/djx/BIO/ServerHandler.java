package com.djx.BIO;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.anxpp.utils.Calculator;


public class ServerHandler implements Runnable {
	private Socket socket;
	public ServerHandler(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket=socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedReader in=null;
		PrintWriter out=null;
		try {
			in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out=new PrintWriter(socket.getOutputStream(),true);
			String expression;
			String result;
			while(true){
				if((expression=in.readLine())==null) break;
				System.out.println("服务器收到消息："+expression);
				try {
					result=Calculator.cal(expression).toString();
				} catch (Exception e) {
					// TODO: handle exception
					result="计算错误"+e.getMessage();
				}
				out.println(result);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			in=null;
		}
		if(out!=null){
			out.close();
			out=null;
		}
		if(socket!=null){
			try {
				socket.close();
				socket=null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
