package org.simbiosis.miniatm.httpclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import android.annotation.SuppressLint;

public class HttpsClient {

	private String host = "";
	private String strUrl = "";
	StringBuilder dataToSend = new StringBuilder();

	public HttpsClient(String host, String path) {
		// Jangan pakai SNI extension
		System.setProperty("jsse.enableSNIExtension", "false");
		//
		this.host = host;
		strUrl = "https://" + host + "/" + path;
	}

	@SuppressLint("TrulyRandom")
	private void init() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}

			public void checkClientTrusted(X509Certificate[] certs,
					String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs,
					String authType) {
			}
		} };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}

		// use hostname
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

			public boolean verify(String hostname, SSLSession sslSession) {
				if (hostname.equals(host)) {
					return true;
				}
				return false;
			}
		});
	}

	public void addParameter(String name, String value) {
		try {
			if (dataToSend.length() != 0)
				dataToSend.append('&');
			dataToSend.append(URLEncoder.encode(name, "UTF-8"));
			dataToSend.append('=');
			dataToSend.append(URLEncoder.encode(value, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String get() {
		//
		init();
		//
		String result = "";
		try {
			// Write request
			URL url = new URL(strUrl
					+ (dataToSend.toString().isEmpty() ? "" : "?"
							+ dataToSend.toString()));
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// Read response
			Reader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			for (int c; (c = in.read()) >= 0; result += ((char) c))
				;
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String post() {
		//
		init();
		//
		String result = "";
		try {
			// Write request
			URL url = new URL(strUrl);
			byte[] postDataBytes = dataToSend.toString().getBytes("UTF-8");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length",
					String.valueOf(postDataBytes.length));
			// Write data to send
			conn.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.write(postDataBytes);
			wr.flush();
			wr.close();
			// Read response
			Reader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			for (int c; (c = in.read()) >= 0; result += ((char) c))
				;
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
