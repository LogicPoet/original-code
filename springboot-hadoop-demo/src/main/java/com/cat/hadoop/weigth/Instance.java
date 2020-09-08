package com.cat.hadoop.weigth;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chosen
 *
 * 一个观测实例，包含事件和时间发生的环境
 *
 * @date 2020/7/10 4:29 下午
 */
public  class Instance
{
	/**
	 * 结果标签
	 */
	String label;
	/**
	 * 因素标签集合，如[Sunny, Happy]
	 */
	List<String> fieldList = new ArrayList<String>();

	public Instance(String label, List<String> fieldList)
	{
		this.label = label;
		this.fieldList = fieldList;
	}
}
