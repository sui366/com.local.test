package com.test.elasticsearch.test1;

import java.beans.PropertyDescriptor;
import java.io.IOException;

import org.apache.commons.beanutils.PropertyUtils;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public class JsonUtil {

	/**
	 * 实现将实体对象转换成json对象
	 * 
	 * @param medicine
	 *            Medicine对象
	 * @return
	 */
	public static String obj2JsonData(Object source) {
		String jsonData = null;
		try {
			// 使用XContentBuilder创建json数据
			XContentBuilder jsonBuild = XContentFactory.jsonBuilder();
//			jsonBuild.startObject().field("id", medicine.getId()).field("name", medicine.getName()).field("funciton", medicine.getFunction()).endObject();
			
			jsonBuild.startObject();
			
			if (null != source ) {
				PropertyDescriptor[] property = PropertyUtils.getPropertyDescriptors(source);

				for (int i = 0; i < property.length; i++) {
					String propertyName = property[i].getName();
					
					if (PropertyUtils.isWriteable(source, propertyName)) {
						try {
							Object propertyValue = PropertyUtils.getProperty(source, propertyName);
							
							jsonBuild.field(propertyName, propertyValue);
							
						} catch (Exception e) {
						}
					}
				}
			}
			jsonBuild.endObject();
			
			jsonData = jsonBuild.string();
			
//			System.out.println(jsonData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonData;
	}

}