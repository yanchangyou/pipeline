package org.software.sphere.society.platform.pipeline.lang.code.flownode.pick;

import java.util.Iterator;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.core.Skillable;



/**
 * if - else if - else ��ʽ���߼��ж�<BR>
 * 
 * �߼��жϹ��� : ������������ִ��, ִ�к����������if-else��<BR>
 * ��֮if-else�����ݱ�֤���ҽ���һ����ִ��
 * 
 * �ڲ�ʵ�� : if, else if, else ������ǩ����If��ʵ��, <BR>
 * else�Ĵ�������� û��condition��If�������Ϊ��else <BR>
 * ������Ҫ�ⲿ��֤�﷨��ȷ---��һ����if, �м�һ����else-if,���һ��������else<BR>
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-8 ����02:15:09
 * @file : IfElse.java
 * @version : 0.1
 */
public class IfElse extends Branch {

//	public void execute(Session clientSession) throws ConnectException, Exception {
//		
//		for (Iterator iter = flowList.iterator(); iter.hasNext();) {
//			If _if = (If) iter.next();
//			DefaultNode1X varMap = getDataNodeMap(clientSession);
//			if (_if.getCondition() ==null || MVEL.evalToBoolean(_if.getCondition(), varMap).booleanValue() == true) {
//				_if.execute(clientSession);
//				break; //if-else����ֻ��ִ��һ��
//			}
//		}
//	}
//	
	
	/**
	 * ���Լ�������
	 */
	public void justDoIt(Skillable skill) throws CoreException {
		for (Iterator iter = nextNodeList.iterator(); iter.hasNext();) {
			Skillable subSkill = (Skillable) iter.next();
			subSkill.justDoIt(subSkill);
		}
	}
}
