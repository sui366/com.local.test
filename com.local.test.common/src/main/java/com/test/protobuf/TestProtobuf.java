package com.test.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class TestProtobuf {
	public static void main(String[] args) {
		Persons.PersonInfo.Builder builder = Persons.PersonInfo.newBuilder();
		builder.setId(1);
		builder.setName("zhang");
		Persons.PersonInfo info = builder.build();
		byte[] result = info.toByteArray();
		System.out.println("===========" + result);

		try {
			Persons.PersonInfo msg = Persons.PersonInfo.parseFrom(result);
			System.out.println(msg.getId());
			System.out.println(msg.getName());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
	}

}