package com.erenwu.common.enums;

/**
 * 枚举基础类
 * @author eren
 */
public interface BaseEnum<T> {
	/**
	 * 枚举value
	 *
	 * @return
	 */
	T getValue();

	/**
	 * 枚举的显示名字
	 *
	 * @return
	 */
	String getDisplayName();
}
