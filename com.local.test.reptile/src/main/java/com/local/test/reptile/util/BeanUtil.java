package com.local.test.reptile.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;


public class BeanUtil {
	private static void copy(Object source, Object target) throws Exception {
		PropertyDescriptor[] property = PropertyUtils.getPropertyDescriptors(target);

		for (int i = 0; i < property.length; i++) {
			String propertyName = property[i].getName();
			
			if (PropertyUtils.isWriteable(source, propertyName)) {
				try {
					Object propertyValue = PropertyUtils.getProperty(source, propertyName);
					
					if(null != propertyValue) {
						PropertyUtils.setProperty(target, propertyName, propertyValue);
					}
				} catch (IllegalAccessException e) {
					throw new Exception(source.getClass().getName() + "转" + target.getClass().getName() + "时发生错误，" + e.getMessage(), e); 
				} catch (InvocationTargetException e) {
					throw new Exception(source.getClass().getName() + "转" + target.getClass().getName() + "时发生错误，" + e.getMessage(), e); 
				} catch (NoSuchMethodException e) {
					throw new Exception(source.getClass().getName() + "转" + target.getClass().getName() + "时发生错误，" + e.getMessage(), e); 
				}
			}
		}
	}
	
	public static void copyProperties(Object source, Object target) throws Exception {
		if (null != source && null != target) {
			copy(source, target);
		}
	}
	
	public static <T> T copyProperties(Object s, Class<T> targetClass) throws Exception {
		if(null == s) {
			return null;
		}
		
		T t = ObjectUtil.newObject(targetClass);
		copyProperties(s, t);
		
		return t;
	}
	
	public static <T, S> List<T> copyObjList(List<S> srcList, Class<T> targetClass) throws Exception {
		List<T> targetList = new ArrayList<T>();
		
		if(null != srcList && !srcList.isEmpty()) {
			for(S s : srcList) {
				T t = ObjectUtil.newObject(targetClass);
				copyProperties(s, t);
				targetList.add(t);
			}
		}
		
		return targetList;
	}
	
}
