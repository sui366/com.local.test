package com.test.httpClient;

import java.util.concurrent.TimeUnit;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;

public class Test1 {
	public static void main(String[] args) throws Exception {

		// CloseableHttpClient httpclient = HttpClients.createDefault();
		// HttpGet httpGet = new HttpGet("http://targethost/homepage");
		// CloseableHttpResponse response1 = httpclient.execute(httpGet);
		// // The underlying HTTP connection is still held by the response
		// object
		// // to allow the response content to be streamed directly from the
		// network socket.
		// // In order to ensure correct deallocation of system resources
		// // the user MUST call CloseableHttpResponse#close() from a finally
		// clause.
		// // Please note that if response content is not fully consumed the
		// underlying
		// // connection cannot be safely re-used and will be shut down and
		// discarded
		// // by the connection manager.
		// try {
		// System.out.println(response1.getStatusLine());
		// HttpEntity entity1 = response1.getEntity();
		// // do something useful with the response body
		// // and ensure it is fully consumed
		// EntityUtils.consume(entity1);
		// } finally {
		// response1.close();
		// }
		//
		// HttpPost httpPost = new HttpPost("http://targethost/login");
		// List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		// nvps.add(new BasicNameValuePair("username", "vip"));
		// nvps.add(new BasicNameValuePair("password", "secret"));
		// httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		// CloseableHttpResponse response2 = httpclient.execute(httpPost);
		//
		// try {
		// System.out.println(response2.getStatusLine());
		// HttpEntity entity2 = response2.getEntity();
		// // do something useful with the response body
		// // and ensure it is fully consumed
		// EntityUtils.consume(entity2);
		// } finally {
		// response2.close();
		// }
		//
		// URI uri = new URIBuilder()
		// .setScheme("http")
		// .setHost("www.google.com")
		// .setPath("/search")
		// .setParameter("q", "httpclient")
		// .setParameter("btnG", "Google Search")
		// .setParameter("aq", "f")
		// .setParameter("oq", "")
		// .build();
		// HttpGet httpget = new HttpGet(uri);
		// System.out.println(httpget.getURI());
		//

	}

	@Test
	public void keepAlive() {
		ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {

			@Override
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				long keepAlive = super.getKeepAliveDuration(response, context);
				if (keepAlive == -1) {
					// Keep connections alive 5 seconds if a keep-alive value
					// has not be explicitly set by the server
					keepAlive = 5000;
				}
				return keepAlive;
			}

		};
		CloseableHttpClient httpclient = HttpClients.custom().setKeepAliveStrategy(keepAliveStrat).build();
	}
	
	@Test
	public void test3() throws Exception{
		HttpClientContext context = HttpClientContext.create();
		HttpClientConnectionManager connMrg = new BasicHttpClientConnectionManager();
		HttpRoute route = new HttpRoute(new HttpHost("localhost", 80));
		// Request new connection. This can be a long process
		ConnectionRequest connRequest = connMrg.requestConnection(route, null);
		// Wait for connection up to 10 sec
		HttpClientConnection conn = connRequest.get(10, TimeUnit.SECONDS);
		try {
		    // If not open
		    if (!conn.isOpen()) {
		        // establish connection based on its route info
		        connMrg.connect(conn, route, 1000, context);
		        // and mark it as route complete
		        connMrg.routeComplete(conn, route, context);
		    }
		    // Do useful things with the connection.
		} finally {
		    connMrg.releaseConnection(conn, null, 1, TimeUnit.MINUTES);
		}
	}
}
