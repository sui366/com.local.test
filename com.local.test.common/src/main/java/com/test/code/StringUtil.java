package com.test.code;

public class StringUtil {

	
	public static String transProperties(String cloumnName){
		StringBuffer str = new StringBuffer();
		
		boolean mark = false;
		char[] words = cloumnName.toCharArray();
		for(char word:words){
			if(word == '_'){
				mark = true;
			}else{
				if(mark){
					str.append(String.valueOf(word).toUpperCase());
					mark = false;
				}else{
					str.append(word);
				}
			}
		}
		
		return str.toString();
	}
	
	public static String transEnityName(String cloumnName){
		String  str = transProperties(cloumnName);
		char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
	}
	
	public static String getMethod(String cloumnName){
		String string = transEnityName(cloumnName);
		return "get" + string;
	}
	
	public static String setMethod(String cloumnName){
		String string = transEnityName(cloumnName);
		return "set" + string;
	}
	
	public static StringBuffer writeLine(int count, String str, StringBuffer s) {
		for(int i=0; i< count; i++){
			s.append("  ");
		}
		return s.append(str).append("\n");
	}
	
	public static String getJavaType(String dbType){
		String javaType = "String";
		for(ColumnType type : ColumnType.values()){
			if(type.getDbType().equals(dbType.toLowerCase())){
				javaType = type.getJavaType();
				break;
			}
		}
		return javaType;
	}
	
	public static String getDBType(String dbType){
		if(dbType.toLowerCase().equals("int"))
			return "INTEGER";
		return dbType.toUpperCase();
	}
	
	
	public static StringBuffer strSub(StringBuffer str, char c) {
		boolean mark = false;
		if(str.charAt(str.length()-1) == '\n'){
			str = str.deleteCharAt(str.length()-1);
			mark = true;
		}
		if(c == str.charAt(str.length()-1)){
			if(mark){
				return str.deleteCharAt(str.length()-1).append('\n');
			}else{
				
				return str.deleteCharAt(str.length()-1);
			}
		}else{
			if(mark){
				return str.append('\n');
			}else{
				return str;
			}
		}
	}
	
	enum ColumnType{
		INT("int", "Integer"),
		BIGINT("bigint", "Long"),
		VARCHAR("varchar", "String"),
		TIMESTAMP("timestamp", "Date"),
		INTTYPE("integer", "Integer");
		
		String dbType;
		
		String javaType;
		
		private ColumnType(String dbType, String javaType) {
			this.dbType = dbType;
			this.javaType = javaType;
		}
		
		public String getDbType() {
			return dbType;
		}
		
		public String getJavaType() {
			return javaType;
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(StringUtil.transProperties("name_ddd_ddd"));
		System.out.println(StringUtil.transEnityName("name_ddd_ddd"));
		
		StringBuffer sBuffer = new  StringBuffer();
		sBuffer.append("dddd");
		
		
		System.out.println(StringUtil.ColumnType.BIGINT.getJavaType());
	}

	
	
}
