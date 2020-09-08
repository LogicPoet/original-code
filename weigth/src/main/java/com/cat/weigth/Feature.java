package com.cat.weigth;

/**
 * @author chosen
 * @date 2020/7/10 4:30 下午
 *
 * 特征(二值函数)
 */
public class Feature
{
	/**
	 * 结果标签
	 */
	String label;
	/**
	 * 因素标签
	 */
	String value;

	/**
	 * 特征函数
	 * @param result 结果
	 * @param value 环境
	 */
	public Feature(String result, String value)
	{
		this.label = result;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj)
	{
		Feature feature = (Feature) obj;
		if (this.label.equals(feature.label) && this.value.equals(feature.value))
			return true;
		return false;
	}

	@Override
	public String toString()
	{
		return "[" + label + ", " + value + "]";
	}

}
