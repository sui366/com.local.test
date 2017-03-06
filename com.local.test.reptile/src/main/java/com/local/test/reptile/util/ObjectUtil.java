package com.local.test.reptile.util;

import org.apache.log4j.Logger;

/**
 * 对象工具类
 * 
 */
public abstract class ObjectUtil {

	private static Logger logger = Logger.getLogger(ObjectUtil.class);

	/**
	 * 若对象为空则返回new对象，否则不变
	 * 
	 * @param value
	 * @param targetClass
	 * @return
	 */
	public static <T> T trim(T value, Class<T> targetClass) {
		if (value == null && targetClass != null) {
			try {
				value = targetClass.newInstance();
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return value;
	}

	public static <T> T newObject(Class<T> targetClass) {
		try {
			return targetClass.newInstance();
		} catch (Exception e) {
			logger.error("", e);
		}

		return null;
	}
}