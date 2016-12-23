//package com.test.socketlite;
//
//import java.nio.ByteBuffer;
//import java.util.concurrent.ConcurrentHashMap;
//
//import org.threadly.concurrent.future.SettableListenableFuture;
//import org.threadly.litesockets.Client;
//import org.threadly.litesockets.Client.Reader;
//import org.threadly.litesockets.Server.ClientAcceptor;
//import org.threadly.litesockets.TCPClient;
//import org.threadly.litesockets.TCPServer;
//import org.threadly.litesockets.ThreadedSocketExecuter;
//import org.threadly.litesockets.utils.MergedByteBuffers;
//
//public class LiteSocketServer {
//
//	private void server() throws Exception {
//		final String hello = "hello\n";
//		final String echo = "ECHO: ";
//		final SettableListenableFuture<Object> exitSent = new SettableListenableFuture<Object>(false);
//
//		// We use a concurrentMap since the Servers Accept callback can happen
//		// on any thread in the threadpool
//		final ConcurrentHashMap<Client, MergedByteBuffers> clients = new ConcurrentHashMap<Client, MergedByteBuffers>();
//
//		// This is the SocketExecuter this runs the selector and adds
//		// reads/writes to the clients
//		// as well as runs the call backs. By default this is a
//		// singleThreadPool, a threadpool
//		// Can be passed in if more threads are needed.
//		final ThreadedSocketExecuter TSE = new ThreadedSocketExecuter();
//		TSE.start();
//
//		// We create a listen socket here. The socket is opened but nothing can
//		// be accepted
//		// Untill we run start on it.
//		TCPServer server = TSE.createTCPServer("localhost", 5555);
//
//		// Here we set the ClientAcceptor callback. This is what is called when
//		// a new client connects to the server.
//		server.setClientAcceptor(new ClientAcceptor() {
//			@Override
//			public void accept(final Client newClient) {
//				final TCPClient tc = (TCPClient) newClient;
//
//				// Add the client to the map with a queue
//				clients.put(newClient, new MergedByteBuffers());
//
//				// Set the clients reader, any data sent before it added will be
//				// called as soon as we add the reader.
//				tc.setReader(new Reader() {
//					@Override
//					public void onRead(Client client) {
//						MergedByteBuffers mbb = client.getRead();
//						if (mbb.indexOf("exit") > -1) {
//							exitSent.setResult("");
//						} else {
//							// We just assume everything is a string
//							String str = mbb.getAsString(mbb.remaining());
//							clients.get(client).add(ByteBuffer.wrap(str.getBytes()));
//							client.write(ByteBuffer.wrap((echo + str).getBytes()));
//						}
//					}
//				});
//				// Here we set the closer for the client. This will be called
//				// only once when the socket is closed.
//				// This also happens in a single threaded manor and should be
//				// after all the reads are processed for the client.
//				tc.setCloser(new Closer() {
//					@Override
//					public void onClose(Client client) {
//						// Normally you would want to clean up client state
//						// here, but we save everything for this servers exit.
//						// MergedByteBuffers mbb = clients.remove(client);
//						if (mbb.remaining() > 0) {
//							// If the client sent something write it to stdout
//							// once it closed
//							System.out.println("Client Wrote:" + mbb.getAsString(mbb.remaining()));
//						}
//					}
//				});
//
//				// Write hello to client. The Socket executer will deal with
//				// getting it
//				// onto the actual socket.
//				newClient.write(ByteBuffer.wrap(hello.getBytes()));
//			}
//		});
//
//		// Here we add the server. At this point we can accept client
//		// connections.
//		server.start();
//
//		// Wait for an exit from the client.
//		exitSent.get();
//
//		// Print out every client connected and what they sent
//		for (Client client : clients.keySet()) {
//			MergedByteBuffers mbb = clients.get(client);
//			System.out.println("Client:" + client + ":\n\tsent data:\n" + mbb.getAsString(mbb.remaining()));
//			System.out.println("--------------------");
//		}
//	}
//
//	public static void main(String[] args) {
//
//	}
//}
