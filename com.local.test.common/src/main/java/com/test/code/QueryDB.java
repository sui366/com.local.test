package com.test.code;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QueryDB {

	static String sql = null;
	static DBHelper db1 = null;
	static ResultSet ret = null;
	
	String tableName;
	
	public QueryDB(String tableName) {
		this.tableName = tableName;
	}
	
	public List<Entity> query(){
		sql = "SELECT column_name,data_type,column_comment from information_schema.columns WHERE  table_name = '"+tableName+"'";//SQL语句
		db1 = new DBHelper(sql);//创建DBHelper对象
		System.out.println(sql);
		List<Entity> list = new ArrayList<Entity>();
		try {
			ret = db1.pst.executeQuery();//执行语句，得到结果集
			Entity entity;
			while (ret.next()) {
				entity = new Entity();
				entity.setCloumnName(ret.getString(1));
				entity.setDataType(ret.getString(2));
				entity.setComment(ret.getString(3));
				list.add(entity);
			}//显示数据
			ret.close();
			db1.close();//关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		
	}
	
	

}

