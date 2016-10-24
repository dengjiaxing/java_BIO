package com.djx.BIO;

import java.io.IOException;
import java.util.Random;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					ServerNormal.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		Thread.sleep(100);
		char[] operators={'+','-','*','/'};
		Random random=new Random(System.currentTimeMillis());
		new Thread(new  Runnable() {
			@Override
			public void run() {
				while(true){
					String expression = random.nextInt(10)+""+operators[random.nextInt(4)]+(random.nextInt(10)+1);
					Client.send(expression);
					  try {
					Thread.currentThread().sleep(random.nextInt(1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
