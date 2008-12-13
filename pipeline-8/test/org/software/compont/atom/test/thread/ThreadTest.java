package org.software.compont.atom.test.thread;

public class ThreadTest {

	public static void main(String[] args) throws InterruptedException {
		PrintThread[] pArray = new PrintThread[] { new PrintThread("A"),
				new PrintThread("B"), new PrintThread("C"), new PrintThread("D"),
				new PrintThread("E") };
		
		for (int i = 0; i < pArray.length; i++) {
			pArray[i].start();
		}

		for (int i = 0; i < pArray.length; i++) {
			pArray[i].join();
		}
		System.out.println("this is end print");

	}
}

class PrintThread extends java.lang.Thread {
	private String msg;

	private int repeatTimes;

	private int interval;

	public PrintThread(String msg, int repeatTimes, int interval) {
		this.msg = msg;
		this.repeatTimes = repeatTimes;
		this.interval = interval;
	}

	public PrintThread(String msg, int repeatTimes) {
		this(msg, repeatTimes, 100);
	}

	public PrintThread(String msg) {
		this(msg, 10);
	}

	public void run() {
		for (int i = 0; i < repeatTimes; i++) {
			System.out.println(msg);
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
