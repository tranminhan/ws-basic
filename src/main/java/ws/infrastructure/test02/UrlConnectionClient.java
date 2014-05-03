package ws.infrastructure.test02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnectionClient {
	public static void main(String[] args) throws IOException {
		String urlString = "http://edition.cnn.com/";
		URL url = new URL(urlString);

		URLConnection socket = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		String nextRecord = null;

		while ((nextRecord = reader.readLine()) != null) {
			System.out.println(nextRecord);
		}

		reader.close();
	}
}
