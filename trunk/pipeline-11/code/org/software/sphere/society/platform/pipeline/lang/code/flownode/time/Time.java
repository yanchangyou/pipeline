package org.software.sphere.society.platform.pipeline.lang.code.flownode.time;

import java.net.ConnectException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.time.DateUtils;
import org.software.sphere.society.platform.pipeline.lang.Session;
import org.software.sphere.society.platform.pipeline.lang.code.flownode.FlowNode;

public class Time extends FlowNode {
	
	java.lang.String firstTime;//��һ��ִ��ʱ��
	
	java.lang.String delay; //��һ��ִ��ʱ���ӳٶ���
	
	java.lang.String period; // �ظ�����
	
	java.lang.String maxTimes; //����ظ�����
	
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

	public void execute(final Session clientSession) throws ConnectException, Exception {

		final Timer timer = new Timer();
		TimerTask newTimerTask = new TimerTask () {
			long time = 0;
			public void run() {
				try {
					Time.super.defaultExecute(clientSession);
				} 
//				catch (ConnectException e) {
////					e.printStackTrace();
//					log.error("���Ӵ���, �Զ��˳�, ������Ϣ:"  + e.getMessage());
//					timer.cancel();
//				}
				catch (Exception e) {
//					e.printStackTrace();
					log.error("�쳣����, �Զ��˳�, ������Ϣ:"  + e.getMessage());
					timer.cancel();
				}
				time ++;
				
				if (time > Long.parseLong(Time.this.maxTimes)) {
					timer.cancel();
				}
			}
			
		};
		Date firstDateTime = null;
		if (this.firstTime == null) {
			firstDateTime = new Date();
		} else {
			firstDateTime = DateUtils.parseDate(firstTime, new java.lang.String[]{"yyyy-MM-dd hh:mm:ss"});
		}
		
		if (delay == null) {
			delay = "0";
		}
		if (period == null) {
			period = "1000";
		}
		
		if (maxTimes == null) {
			maxTimes = "1";
		}
		
		Date realExecuteTime = new Date(firstDateTime.getTime() + Long.parseLong(this.delay));
		timer.scheduleAtFixedRate(newTimerTask , realExecuteTime, Long.parseLong(this.period));
	}
}
