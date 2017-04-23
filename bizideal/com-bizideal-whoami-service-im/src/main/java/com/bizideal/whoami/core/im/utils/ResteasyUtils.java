package com.bizideal.whoami.core.im.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.NameValuePair;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.cache.BrowserCacheFeature;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bizideal.whoami.core.im.common.HTTPMethod;
import com.bizideal.whoami.core.im.vo.Credential;
import com.bizideal.whoami.core.im.vo.Token;


/**
 * @ClassName ResteasyUtils
 * @Description TODO(Resteasy工具类)
 * @Author Zj.Qu
 * @Date 2017-01-04 10:15:04
 */
public class ResteasyUtils {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResteasyUtils.class);
	
	private static final JsonNodeFactory factory = JsonNodeFactory.instance;
	

	/**
	 * Send HTTPS request with Resteasy
	 * 
	 * @return 
	 */
	public static ObjectNode sendRequest(ResteasyWebTarget resteasyWebTarget, Object body, Credential credential,
			String method, List<NameValuePair> headers) throws RuntimeException {

		ObjectNode objectNode = factory.objectNode();

		if (!match("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", resteasyWebTarget.getUri().toString())) {
			LOGGER.error("The URL to request is illegal");
			objectNode.put("message", "The URL to request is illegal");
			return objectNode;
		}

		try {

			Invocation.Builder inBuilder = resteasyWebTarget.request();
			if (credential != null) {
				 Token.applyAuthentication(inBuilder, credential);
			}

			if (null != headers && !headers.isEmpty()) {

				for (NameValuePair nameValuePair : headers) {
					inBuilder.header(nameValuePair.getName(), nameValuePair.getValue());
				}

			}

			Response response = null;
			if (HTTPMethod.METHOD_GET.equals(method)) {

				response = inBuilder.get(Response.class);

			} else if (HTTPMethod.METHOD_POST.equals(method)) {

				response = inBuilder.post(Entity.entity(body, MediaType.APPLICATION_JSON), Response.class);

			} else if (HTTPMethod.METHOD_PUT.equals(method)) {

				response = inBuilder.put(Entity.entity(body, MediaType.APPLICATION_JSON), Response.class);

			} else if (HTTPMethod.METHOD_DELETE.equals(method)) {

				response = inBuilder.delete(Response.class);

			}

			objectNode = response.readEntity(ObjectNode.class);
			objectNode.put("statusCode", response.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * DownLoadFile whit Resteasy
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static File downLoadFile(ResteasyWebTarget resteasyWebTarget, Credential credential,
			List<NameValuePair> headers, File localPath) throws IOException {

		Invocation.Builder inBuilder = resteasyWebTarget.request();

		if (credential != null) {
            Token.applyAuthentication(inBuilder, credential);
		}

		if (null != headers && !headers.isEmpty()) {

			for (NameValuePair nameValuePair : headers) {
				inBuilder.header(nameValuePair.getName(), nameValuePair.getValue());
			}

		}

		File file = inBuilder.get(File.class);
		file.renameTo(localPath);
		FileWriter fr = new FileWriter(file);

		fr.flush();
		fr.close();
		return file;

	}

	/**
	 * UploadFile whit Resteasy
	 * 
	 * @return
	 */
	public static ObjectNode uploadFile(ResteasyWebTarget resteasyWebTarget, File file, Credential credential,
			List<NameValuePair> headers) throws RuntimeException {
		
		ObjectNode objectNode = factory.objectNode();

		try {

			Invocation.Builder inBuilder = resteasyWebTarget.request();
			if (credential != null) {
				Token.applyAuthentication(inBuilder, credential);
			}

			if (null != headers && !headers.isEmpty()) {
				for (NameValuePair nameValuePair : headers) {
					inBuilder.header(nameValuePair.getName(), nameValuePair.getValue());
				}
			}

			MultipartFormDataOutput multiPart = new MultipartFormDataOutput();
			multiPart.addFormData("file", new FileInputStream(file),MediaType.APPLICATION_OCTET_STREAM_TYPE);

			objectNode = inBuilder.post(Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA), ObjectNode.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * Check illegal String
	 * 
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean match(String regex, String str) {
		//ResteasyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.lookingAt();
	}

	/**
	 * Obtain a ResteasyClient whit SSL
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static ResteasyClient getResteasyClient(boolean isSSL) {
		
		ClientBuilder clientBuilder = ResteasyClientBuilder.newBuilder().register(BrowserCacheFeature.class);

		// Create a secure ResteasyClient
		if (isSSL) {
			try {
				HostnameVerifier verifier = new HostnameVerifier() {
					
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
					
				};

				TrustManager[] tm = new TrustManager[] { new X509TrustManager() {

					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					@Override
					public void checkServerTrusted(X509Certificate[] chain, String authType)
							throws CertificateException {
					}

					@Override
					public void checkClientTrusted(X509Certificate[] chain, String authType)
							throws CertificateException {
					}

				} };

				SSLContext sslContext = SSLContext.getInstance("SSL");

				sslContext.init(null, tm, new SecureRandom());

				clientBuilder.sslContext(sslContext).hostnameVerifier(verifier);
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (KeyManagementException e) {
				e.printStackTrace();
			}
		}

		return (ResteasyClient) clientBuilder.build().register(JacksonJsonProvider.class);
	}

}
