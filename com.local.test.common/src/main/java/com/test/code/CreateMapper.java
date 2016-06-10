package com.test.code;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateMapper {

	StringBuffer sb = new StringBuffer();
	
	
	public  CreateMapper(String tableName) {
		
		List<Entity> entityList = new QueryDB(tableName).query();
		
		StringUtil.writeLine(0, "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>", sb);
		StringUtil.writeLine(0, "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >", sb);
		StringUtil.writeLine(0, "<mapper namespace=\""+ StringUtil.transEnityName(tableName) +"Dao\" >", sb);
		StringUtil.writeLine(1, "<resultMap id=\"BaseResultMap\" type=\""+ StringUtil.transEnityName(tableName) +"\" >", sb);
		for(Entity entity : entityList){
			StringUtil.writeLine(2, "<id column=\""+ entity.getCloumnName() +"\" property=\""+ StringUtil.transProperties(entity.getCloumnName()) +"\" jdbcType=\""+ StringUtil.getDBType(entity.getDataType().toUpperCase()) +"\" />", sb);
		}
		StringUtil.writeLine(1, "  </resultMap>", sb);
		StringUtil.writeLine(0, "", sb);
		
		
		
		
		StringUtil.writeLine(1, "<insert id=\"save\" parameterType=\"\">", sb);
		
		StringUtil.writeLine(2, "<selectKey resultType=\"int\" keyProperty=\"id\" >", sb);
		StringUtil.writeLine(3, "select LAST_INSERT_ID() as value ", sb);
		StringUtil.writeLine(2, "</selectKey>", sb);
		
		StringUtil.writeLine(2, "insert into "+ tableName +" (", sb);
		for(Entity entity : entityList){
			StringUtil.writeLine(3, entity.getCloumnName()+",", sb);
		}
		sb = StringUtil.strSub(sb, ',');
		
		StringUtil.writeLine(2, " ) values (", sb);
		
		for(Entity entity : entityList){
			StringUtil.writeLine(2, "#{"+ StringUtil.transProperties(entity.getCloumnName()) +",jdbcType="+ StringUtil.getDBType(entity.getDataType().toUpperCase()) +"},", sb);
		}
		sb = StringUtil.strSub(sb, ',');
		
		StringUtil.writeLine(2, " )", sb);
		
		StringUtil.writeLine(2, "</insert>", sb);
		
		
		
		
		
		
		StringUtil.writeLine(1, "<update id=\"update\" parameterType=\"\" >", sb);
		
		StringUtil.writeLine(2, "update "+tableName, sb);
		StringUtil.writeLine(2, "set", sb);
		for(Entity entity : entityList){
			if(!entity.getCloumnName().toLowerCase().equals("id")){
				StringUtil.writeLine(3, entity.getCloumnName() +" = #{"+ StringUtil.transProperties(entity.getCloumnName()) +",jdbcType="+ StringUtil.getDBType(entity.getDataType().toUpperCase()) +"},", sb);
			}
		}
		sb = StringUtil.strSub(sb, ',');
		StringUtil.writeLine(2, "where", sb);
		StringUtil.writeLine(2, "id = #{id,jdbcType=INTEGER}", sb);
		StringUtil.writeLine(1, "</update>", sb);
		
		
		
		StringUtil.writeLine(1, "<select id=\"get\" resultMap=\"BaseResultMap\" parameterType=\"java.lang.Long\" >", sb);
		StringUtil.writeLine(2, "select", sb);
		for(Entity entity : entityList){
			StringUtil.writeLine(3, "t."+entity.getCloumnName()+",", sb);
		}
		sb = StringUtil.strSub(sb, ',');
		StringUtil.writeLine(2, "from "+ tableName +" t ", sb);
		StringUtil.writeLine(3, " where t.id = #{id,jdbcType=INTEGER} ", sb);
		StringUtil.writeLine(1, "</select>", sb);
		
		
		StringUtil.writeLine(0, "</mapper>", sb);
		StringUtil.writeLine(0, "", sb);
		
		
		
		
		/**********************************************************************/
		
		//创建目录
        String dirName = "D:/temp/";
        OutFile.createDir(dirName);
        //创建文件
        String fileName = dirName + StringUtil.transEnityName(tableName) + "Mapper.xml";
        OutFile.createFile(fileName);

        try {
			BufferedWriter output = new BufferedWriter(new FileWriter(fileName));  
			output.write(sb.toString());  
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new CreateMapper("system");
	}

}
