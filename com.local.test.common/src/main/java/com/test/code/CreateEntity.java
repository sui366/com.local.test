package com.test.code;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateEntity {

	StringBuffer sb = new StringBuffer();
	
	
	public  CreateEntity(String tableName) {
		
		List<Entity> entityList = new QueryDB(tableName).query();
		
		StringUtil.writeLine(0, "public class "+ StringUtil.transEnityName(tableName) +"  extends BasePojo {", sb);
		for(Entity entity : entityList){
			
			StringUtil.writeLine(1, "//"+entity.getComment(), sb);
			StringUtil.writeLine(1, "private "+ StringUtil.getJavaType(entity.getDataType()) +" "+StringUtil.transProperties(entity.getCloumnName())+";", sb);
		}
		StringUtil.writeLine(0, "", sb);
		
		for(Entity entity : entityList){
			StringUtil.writeLine(1, "public "+ StringUtil.getJavaType(entity.getDataType()) +" "+ StringUtil.getMethod(entity.getCloumnName()) +"() {", sb);
			StringUtil.writeLine(2, "return "+ StringUtil.transProperties(entity.getCloumnName()) +";", sb);
			StringUtil.writeLine(1, "}", sb);
			StringUtil.writeLine(1, "public void "+ StringUtil.setMethod(entity.getCloumnName()) +"("+ StringUtil.getJavaType(entity.getDataType()) +" "+ StringUtil.transProperties(entity.getCloumnName()) +") {", sb);
			StringUtil.writeLine(2, "this."+ StringUtil.transProperties(entity.getCloumnName()) +" = "+ StringUtil.transProperties(entity.getCloumnName()) +";", sb);
			StringUtil.writeLine(1, "}", sb);
		}
		StringUtil.writeLine(0, "}", sb);
		
		
		/**********************************************************************/
		
		//创建目录
        String dirName = "D:/temp/";
        OutFile.createDir(dirName);
        //创建文件
        String fileName = dirName + "/" + StringUtil.transEnityName(tableName) + ".java";
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
		
		new CreateEntity("system");
	}

}
