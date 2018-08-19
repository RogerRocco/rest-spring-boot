package roger.training.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("static-access")
public class HttpRequestForm {

	private static final Logger logger = LoggerFactory.getLogger(HttpRequestForm.class);

	private final static String USER_AGENT = "Mozilla/5.0";

	public static String sendPost(HttpPost http, String json, HttpServletResponse servletResponse) {
		if (json != null) {
			try {
				HttpClient client = HttpClientBuilder.create().build();

				http.setHeader("User-Agent", USER_AGENT);

				List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
				urlParameters.add(new BasicNameValuePair("json", json));

				logger.warn(json);

				http.setEntity(new UrlEncodedFormEntity(urlParameters));

				HttpResponse response = client.execute(http);
				System.out.println("\nSending '" + http.METHOD_NAME + "' request to URL : " + http.getURI());
				System.out.println(http.METHOD_NAME + " parameters : " + http.getEntity());
				System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				servletResponse.setStatus(201);
				return servletResponse.getStatus() + " : Bankslip created";
			} catch (Exception e) {
				servletResponse.setStatus(422);
				return servletResponse.getStatus() + " : Invalid bankslip provided";
			}
		} else {
			servletResponse.setStatus(400);
			servletResponse.setStatus(400);
			return servletResponse.getStatus() + " : Bankslip not provided in the request body";
		}
	}

	public static String sendPut(HttpPut http, String id, String json, HttpServletResponse servletResponse) {
		try {
			HttpClient client = HttpClientBuilder.create().build();

			http.setHeader("User-Agent", USER_AGENT);

			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("json", json));

			logger.warn(json);

			http.setEntity(new UrlEncodedFormEntity(urlParameters));

			HttpResponse response = client.execute(http);
			System.out.println("\nSending '" + http.METHOD_NAME + "' request to URL : " + http.getURI());
			System.out.println(http.METHOD_NAME + " parameters : " + http.getEntity());
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			servletResponse.setStatus(200);
			return servletResponse.getStatus() + " : Bankslip paid";
		} catch (Exception e) {
			servletResponse.setStatus(404);
			return servletResponse.getStatus() + " : Bankslip not found with the specified id";
		}

	}

	public static String sendGet(HttpGet http, HttpServletResponse servletResponse) {
		StringBuilder sb = new StringBuilder();
		try {
			HttpClient client = HttpClientBuilder.create().build();

			http.addHeader("User-Agent", USER_AGENT);

			HttpResponse response = client.execute(http);

			System.out.println("\nSending '" + http.METHOD_NAME + "' request to URL : " + http.getURI());
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";

			while ((line = rd.readLine()) != null) {
				result.append(line);
				sb.append(line);
			}
			servletResponse.setStatus(200);
		} catch (Exception e) {
			servletResponse.setStatus(404);
			e.printStackTrace();
		}
		return sb.toString().replaceAll("\\/", "");
	}

}
