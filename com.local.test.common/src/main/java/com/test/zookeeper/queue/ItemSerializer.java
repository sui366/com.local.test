package com.test.zookeeper.queue;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.apache.curator.framework.recipes.queue.QueueSerializer;

public class ItemSerializer {
	private static final int VERSION = 0x00010001;

	private static final byte ITEM_OPCODE = 0x01;
	private static final byte EOF_OPCODE = 0x02;

	private static final int INITIAL_BUFFER_SIZE = 0x1000;

	public static <T> byte[] serialize(T item, QueueSerializer<T> serializer) throws Exception {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream(INITIAL_BUFFER_SIZE);
		DataOutputStream out = new DataOutputStream(bytes);
		out.writeInt(VERSION);

		byte[] itemBytes = serializer.serialize(item);
		out.writeByte(ITEM_OPCODE);
		out.writeInt(itemBytes.length);
		if (itemBytes.length > 0) {
			out.write(itemBytes);
		}

		out.writeByte(EOF_OPCODE);
		out.close();

		return bytes.toByteArray();
	}
}