package org.software.sphere.society.platform.pipeline.lang.code.flownode.time;

import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.time.DateUtils;
import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.code.flownode.FlowNode;
import org.software.sphere.society.platform.pipeline.lang.core.Skillable;

public class Time extends FlowNode {
	
	java.lang.String firstTime;//第一次执行时间
	
	java.lang.String delay; //第一次执行时间延迟多少
	
	java.lang.String period; // 重复周期
	
	java.lang.String maxTimes; //最大重复次数
	
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

	public void justDoIt(final Skillable skill) throws CoreException {

		final Timer timer = new Timer();
		TimerTask newTimerTask = new TimerTask () {
			long time = 0;
			public void run() {
				try {
					Time.super.defaultJustDoIt(skill);
				} 
//				catch (ConnectException e) {
////					e.printStackTrace();
//					log.error("连接错误, 自动退出, 错误信息:"  + e.getMessage());
//					timer.cancel();
//				}
				catch (Exception e) {
//					e.printStackTrace();
					log.error("异常产生, 自动退出, 错误信息:"  + e.getMessage());
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
			try {
				firstDateTime = DateUtils.parseDate(firstTime, new java.lang.String[]{"yyyy-MM-dd hh:mm:ss"});
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CoreException(e.getMessage());
			}
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
