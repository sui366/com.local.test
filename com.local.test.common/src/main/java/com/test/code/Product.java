package com.test.code;

public class Product {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String tableName = "gem_log";
		
		new CreateEntity(tableName);
		new CreateMapper(tableName);
	}

	
}
