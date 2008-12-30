package org.software.sphere.society.platform.pipeline.lang.code.flownode.pick;

import java.util.Iterator;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.core.Skillable;



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

//	public void execute(Session clientSession) throws ConnectException, Exception {
//		
//		for (Iterator iter = flowList.iterator(); iter.hasNext();) {
//			If _if = (If) iter.next();
//			DefaultNode1X varMap = getDataNodeMap(clientSession);
//			if (_if.getCondition() ==null || MVEL.evalToBoolean(_if.getCondition(), varMap).booleanValue() == true) {
//				_if.execute(clientSession);
//				break; //if-else块中只能执行一个
//			}
//		}
//	}
//	
	
	/**
	 * 做自己该做的
	 */
	public void justDoIt(Skillable skill) throws CoreException {
		for (Iterator iter = nextNodeList.iterator(); iter.hasNext();) {
			Skillable subSkill = (Skillable) iter.next();
			subSkill.justDoIt(subSkill);
		}
	}
}
