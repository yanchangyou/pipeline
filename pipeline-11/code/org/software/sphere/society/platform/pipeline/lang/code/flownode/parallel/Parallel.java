package org.software.sphere.society.platform.pipeline.lang.code.flownode.parallel;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.software.sphere.society.platform.pipeline.lang.Session;
import org.software.sphere.society.platform.pipeline.lang.code.flownode.FlowNode;


/**
 * ����ִ����<br>
 * ÿ��ִ�����̵�������һ���߳���ִ��<br>
 * ���ҵȴ�����ִ����Ϻ���ִ�к��������<br>
 * ����ʹ���� Thread���е�join����(ע��join�����ĵ���ʱ��---�������̿�ʼִ�к�ʼ����)
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-7 ����11:14:05
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
