package org.software.sphere.society.platform.pipeline.lang.code.realnode;

import org.software.sphere.society.platform.pipeline.lang.code.Code;


/**
 * 所有现实节点的父类<br>
 * 
 * 每个现实节点由五部分构成<br>
 * 1, 上下文<br>
 * 上下文用于存储数据节点, 便于程序随时存取<br>
 * 2, 服务定义<br>
 * 服务定义用于定义当前节点所拥有的服务,并且每个服务有多个竞争者提供此服务<be> 
 * 3, 数据节点列表<br>
 * 定义当前节点的初始数据节点, 并且处理到上下文中, 所有数据节点都统一存储在上下文中 
 * 4, 前驱节点<br>
 * 父节点用于关联父节点, 便于整个real node 的向前遍历 
 * 5, 后续节点<br>
 * 用于整个real node 的向后遍历
 * 
 * 6, 起始流程节点
 * 
 * 
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-8 下午07:18:53
 * @file : RealNode.java
 * @version : 0.1
 */
public class RealNode extends Code {
}
