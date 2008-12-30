package org.software.sphere.society.platform.pipeline.lang.code.flownode.parallel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.code.flownode.FlowNode;
import org.software.sphere.society.platform.pipeline.lang.core.Skillable;


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

	public void justDoIt(Skillable skill) throws CoreException {

		List flowThreadList = new ArrayList();
		for (Iterator iter = nextNodeList.iterator(); iter.hasNext();) {
			final FlowNode flow = (FlowNode) iter.next();
			Thread thread = newThreadExecuteFlow(skill, flow);
			thread.start();
			flowThreadList.add(thread);
		}
		for (Iterator iter = flowThreadList.iterator(); iter.hasNext();) {
			Thread flowThread = (Thread) iter.next();
			try {
				flowThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CoreException(e.getMessage());
			}
		}
	}

	
	public Thread newThreadExecuteFlow(final Skillable skill, final FlowNode flow) {
		Thread thread = new Thread(){ 
			public void run() {
				try {
					flow.justDoIt(skill);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		return thread;
	}
}
