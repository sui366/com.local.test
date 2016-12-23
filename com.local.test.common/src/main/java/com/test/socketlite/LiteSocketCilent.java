package com.test.socketlite;

import java.nio.ByteBuffer;

import org.threadly.concurrent.future.ListenableFuture;
import org.threadly.concurrent.future.SettableListenableFuture;
import org.threadly.litesockets.Client;
import org.threadly.litesockets.Client.Reader;
import org.threadly.litesockets.TCPClient;
import org.threadly.litesockets.ThreadedSocketExecuter;
import org.threadly.litesockets.utils.MergedByteBuffers;

public class LiteSocketCilent {

	private void cilent() throws Exception {
		final String GET = "GET / HTTP/1.1\r\nUser-Agent: litesockets/3.3.0\r\nHost: www.google.com\r\nAccept: */*\r\n\r\n";
		final SettableListenableFuture<Object> onReadFuture = new SettableListenableFuture<Object>(false);
		// This is the SocketExecuter this runs the selector and adds
		// reads/writes to the clients
		// as well as runs the call backs
		final ThreadedSocketExecuter TSE = new ThreadedSocketExecuter();
		TSE.start();

		// MergedByteBuffers class allows you to put many ByteBuffers into one
		// object
		// To allow you to deal with them in a combined way
		final MergedByteBuffers mbb = new MergedByteBuffers();

		// This makes a connection to the specified host/port
		// The connection is not yet actually made, connect() must be called to
		// do that.
		final TCPClient client = TSE.createTCPClient("www.google.com", 80);

		// Here we set the Reader call back. Everytime the client gets a read
		// This will be executed. This will happen in a single threaded way per
		// client.
		// Because it returns the client that the read happened on you can have
		// 1 Reader for many clients
		// assuming you are watching threading between then on your own.
		client.setReader(new Reader() {
			@Override
			public void onRead(Client returnedClient) {
				mbb.add(returnedClient.getRead());
				System.out.println(mbb.getAsString(mbb.remaining()));
				onReadFuture.setResult("");
			}
		});

		ListenableFuture<?> lf = client.connect();
		lf.get();

		// We tell the client to write data to the socket. Since this is to an
		// http server we send
		// a simple GET request once the server gets that it will send us the
		// response.
		ListenableFuture<?> wlf = client.write(ByteBuffer.wrap(GET.getBytes()));
		// Every write returns a future that will be completed once the write
		// has been handed off to the OS.

		// Wait till we get a response back then continue;
		onReadFuture.get();
	}

	public static void main(String[] args) {

	}
}
