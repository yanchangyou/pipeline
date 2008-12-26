package org.software.sphere.society.platform.pipeline.lang.code.flownode.parallel;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.software.sphere.society.platform.pipeline.lang.Session;
import org.software.sphere.society.platform.pipeline.lang.code.flownode.FlowNode;


/**
 * 并行执行类<br>
 * 每个执行流程单独开启一个线程来执行<br>
 * 并且等待它们执行完毕后再执行后面的流程<br>
 * 这里使用了 Thread类中的join方法(注意join方法的调用时间---所有流程开始执行后开始调用)
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-7 下午11:14:05
 * @file : Parallel.java
 * @version : 0.1
 */
public class Parallel extends FlowNode {

	public void execute(final Session clientSession) throws ConnectException, Exception {

		List flowThreadList = new ArrayList();
		for (Iterator iter = flowList.iterator(); iter.hasNext();) {
			final FlowNode flow = (FlowNode) iter.next();
			Thread thread = newThreadExecuteFlow(clientSession, flow);
			flowThreadList.add(thread);
		}
		for (Iterator iter = flowThreadList.iterator(); iter.hasNext();) {
			Thread flowThread = (Thread) iter.next();
			flowThread.join();
		}
	}

	
	public Thread newThreadExecuteFlow(final Session clientSession, final FlowNode flow) {
		Thread thread = new Thread(){ 
			public void run() {
				try {
					flow.execute(clientSession);
				} catch (ConnectException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		thread.start();
		return thread;
	}
}
