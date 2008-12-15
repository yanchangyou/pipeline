package org.software.compont.atom.test.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	java.lang.String firstTime;//��һ��ִ��ʱ��
	
	java.lang.String delay; //��һ��ִ��ʱ���ӳٶ���
	
	java.lang.String period; // �ظ�����
	
	java.lang.String maxTimes; //����ظ�����
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask newTimerTask = new TimerTask () {

			public void run() {
				System.out.println("run it repeat!");
				
			}
			
		};
		                                         
		timer.scheduleAtFixedRate(newTimerTask , new Date(), 1000);
	}

	public java.lang.String getDelay() {
		return delay;
	}

	public void setDelay(java.lang.String delay) {
		this.delay = delay;
	}

	public java.lang.String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(java.lang.String firstTime) {
		this.firstTime = firstTime;
	}

	public java.lang.String getMaxTimes() {
		return maxTimes;
	}

	public void setMaxTimes(java.lang.String maxTimes) {
		this.maxTimes = maxTimes;
	}

	public java.lang.String getPeriod() {
		return period;
	}

	public void setPeriod(java.lang.String period) {
		this.period = period;
	}
}
