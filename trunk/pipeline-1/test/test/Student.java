/* ====================================================================
 Licensed to the Apache Software Foundation (ASF) under one or more
 contributor license agreements.  See the NOTICE file distributed with
 this work for additional information regarding copyright ownership.
 The ASF licenses this file to You under the Apache License, Version 2.0
 (the "License"); you may not use this file except in compliance with
 the License.  You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ==================================================================== */

package test;

import java.sql.Date;

/**
 * Class description goes here.
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: typeTest.java,v0.1 2008-1-16 œ¬ŒÁ07:21:22 cyyan Exp$
 */
public class Student {

	private String no;

	private String name;

	private String sex;

	private Date birthday;

	private Double height;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}
	
	public void setHeight(double height) {
		this.height = new Double(height);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	/*
	 * ±„”⁄ ‰≥ˆ≤‚ ‘
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "{" + no + ", " + 
		name + ", " + 
		birthday + ", " + 
		sex + ", " + 
		height + "}\n";
	}
	
	
	
	public static void main(String[] args) {
		
	}
}
