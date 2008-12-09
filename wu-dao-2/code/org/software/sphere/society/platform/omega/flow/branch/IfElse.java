package org.software.sphere.society.platform.omega.flow.branch;

import java.net.ConnectException;
import java.util.Iterator;

import org.mvel.MVEL;
import org.software.sphere.society.platform.omega.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.omega.execute.Session;


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

	public void execute(Session clientSession) throws ConnectException, Exception {
		
		for (Iterator iter = flowList.iterator(); iter.hasNext();) {
			If _if = (If) iter.next();
			DefaultNode1X varMap = getVarMap(clientSession);
			if (_if.getCondition() ==null || MVEL.evalToBoolean(_if.getCondition(), varMap).booleanValue() == true) {
				_if.execute(clientSession);
				break; //if-else����ֻ��ִ��һ��
			}
		}
	}
	
	public DefaultNode1X getVarMap(Session clientSession) {
		return new DefaultNode1X();
	}
}
