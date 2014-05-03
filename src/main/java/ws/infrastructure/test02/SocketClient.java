package ws.infrastructure.test02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class SocketClient {
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		URL url = new URL("http://edition.cnn.com/");
		String host = url.getHost();
		String path = url.getPath();
		int port = url.getPort();
		if (port < 0) {
			port = 80;
		}

		String request = "GET " + path + " HTTP/1.1\n";
		request += "host: " + host;
		request += "\n\n";

		Socket socket = new Socket(host, port);
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		writer.println(request);
		writer.flush();

		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String record = null;
		while ((record = reader.readLine()) != null) {
			System.out.println(record);
		}
		socket.close();

	}
}
