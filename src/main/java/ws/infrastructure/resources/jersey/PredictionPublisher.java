package ws.infrastructure.resources.jersey;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.ws.rs.ext.RuntimeDelegate;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class PredictionPublisher {
	static final int port = 9876;
	static final String uri = "/resourcesA";
	static final String url = "http://localhost:" + port + uri;

	public static void main(String[] args) {
		new PredictionPublisher().publish();
	}

	private void publish() {
		HttpServer server = getServer();
		HttpHandler requestHandler = RuntimeDelegate.getInstance()
				.createEndpoint(new JerseyPredictionsApplication(), HttpHandler.class);
		server.createContext(uri, requestHandler);
		server.start();
		msg(server);
	}

	private void msg(HttpServer server) {
		System.out.println("Started server");

		try {
			System.in.read();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		server.stop(0);
	}

	private HttpServer getServer() {
		HttpServer server = null;
		int backlog = 8;
		try {
			server = HttpServer.create(
					new InetSocketAddress("localhost", port), backlog);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return server;
	}

}
