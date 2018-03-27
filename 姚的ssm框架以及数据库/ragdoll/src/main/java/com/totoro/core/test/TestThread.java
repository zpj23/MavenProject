package com.totoro.core.test;

public class TestThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Threadb zi = new Threadb();
		Threada fu = new Threada();
		Thread t =new Thread(fu);
		Thread t1 =new Thread(zi);
		// 设置线程的优先级，数字越大优先级越高
		t.setPriority(1);
		t1.setPriority(2);
		
		t.start();
		
		t1.start();

	}

}
