package org.software.sphere.society.platform.omega.flow.branch;

import java.net.ConnectException;
import java.util.Iterator;

import org.mvel.MVEL;
import org.software.sphere.society.platform.omega.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.omega.execute.Session;


/**
 * if - else if - else 形式的逻辑判断<BR>
 * 
 * 逻辑判断规则 : 如果条件满足就执行, 执行后就跳出整个if-else块<BR>
 * 总之if-else块内容保证有且仅有一个被执行
 * 
 * 内部实现 : if, else if, else 三个标签都用If类实现, <BR>
 * else的处理规则是 没有condition的If对象就认为是else <BR>
 * 这里需要外部保证语法正确---第一个是if, 中间一定是else-if,最后一个必须是else<BR>
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-8 下午02:15:09
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
				break; //if-else块中只能执行一个
			}
		}
	}
	
	public DefaultNode1X getVarMap(Session clientSession) {
		return new DefaultNode1X();
	}
}
