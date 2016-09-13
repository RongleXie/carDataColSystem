package com.yesbulo.cardatacolsystem.tools;

import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.BasicHttpEntity;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.params.BasicHttpParams;

import org.apache.http.params.HttpParams;

import org.apache.http.util.EntityUtils;
import org.json.JSONObject;



import sun.misc.BASE64Decoder;

import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;

import java.security.MessageDigest;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.HashMap;

import java.util.Map;

/**
 * 短信验证功能
 * <p>@Title:PhoneCodeTools</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-13 下午04:52:33
 */
public class PhoneCodeTools {

	private static String UTF8 = "utf-8";

	private static Boolean ISTEST = false;

	private static String HTTP_SSL_IP = "127.0.0.1";

	private static int HTTP_SSL_PORT = 0;

	private static String VERSION = "2014-06-30";// 版本

	private static String REST_SERVER = "https://api.ucpaas.com";// REST服务

	private static String ACCOUNTSID = "1f69dfd3954547125f5f195fc37a8e7a";//

	private static String AUTHTOKEN = "33671ee3da393a1a145368425aec49af";//

	private static String APPID = "0413a1e7d740471f85876148101ab788";// 应用

	private static String TEMPLATEID = "29439";// 模版

	public static DefaultHttpClient getDefaultHttpClient() throws Exception {

		DefaultHttpClient httpclient = null;


		httpclient = new DefaultHttpClient();


		return httpclient;

	}

	public static String templateSMS(String to, String param) {

		String result = "";

		DefaultHttpClient httpclient = null;

		try {

			httpclient = getDefaultHttpClient();

			// 构造请求URL内容

			String timestamp = dateToStr(new Date(), "yyyyMMddHHmmss");// 时间戳

			String sig = ACCOUNTSID + AUTHTOKEN + timestamp;

			String signature = md5Digest(sig);

			String url = new StringBuffer(REST_SERVER).append("/").append(
					VERSION)

			.append("/Accounts/").append(ACCOUNTSID)

			.append("/Messages/templateSMS").append("?sig=")

			.append(signature).toString();

			Map<String, String> map = new HashMap<String, String>();

			map.put("accountSid", ACCOUNTSID);

			map.put("authToken", AUTHTOKEN);

			map.put("appId", APPID);

			map.put("templateId", TEMPLATEID);

			map.put("to", to);

			map.put("param", param);

			String body = new JSONObject(map).toString();
			System.out.println(body);

			body = "{\"templateSMS\":" + body + "}";

			HttpResponse response = get("application/json", ACCOUNTSID,

			AUTHTOKEN, timestamp, url, httpclient, body);

			// 获取响应实体信息

			HttpEntity entity = response.getEntity();

			if (entity != null) {

				result = EntityUtils.toString(entity, "UTF-8");

			}

			// 确保HTTP响应内容全部被读出或者内容流被关闭

			EntityUtils.consume(entity);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			// 关闭连接

			if (httpclient != null) {

				httpclient.getConnectionManager().shutdown();

			}

		}

		return result;

	}

	public static String templateSMS(String to, String param, String templateId) {

		String result = "";

		DefaultHttpClient httpclient = null;

		try {

			httpclient = getDefaultHttpClient();

			// 构造请求URL内容

			String timestamp = dateToStr(new Date(), "yyyyMMddHHmmss");// 时间戳

			String sig = ACCOUNTSID + AUTHTOKEN + timestamp;

			String signature = md5Digest(sig);

			String url = new StringBuffer(REST_SERVER).append("/").append(
					VERSION)

			.append("/Accounts/").append(ACCOUNTSID)

			.append("/Messages/templateSMS").append("?sig=")

			.append(signature).toString();

			Map<String, String> map = new HashMap<String, String>();

			map.put("accountSid", ACCOUNTSID);

			map.put("authToken", AUTHTOKEN);

			map.put("appId", APPID);

			map.put("templateId", templateId);

			map.put("to", to);

			map.put("param", param);

			String body = new JSONObject(map).toString();

			body = "{\"templateSMS\":" + body + "}";

			HttpResponse response = get("application/json", ACCOUNTSID,

			AUTHTOKEN, timestamp, url, httpclient, body);

			// 获取响应实体信息

			HttpEntity entity = response.getEntity();

			if (entity != null) {

				result = EntityUtils.toString(entity, "UTF-8");

			}

			// 确保HTTP响应内容全部被读出或者内容流被关闭

			EntityUtils.consume(entity);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			// 关闭连接

			if (httpclient != null) {

				httpclient.getConnectionManager().shutdown();

			}

		}

		return result;

	}
	
	
	/**
	 * 发送验证码
	 * @param to 手机号码
	 * @param param 验证码内容
	 * @return
	 * String
	 */
	public static boolean send(String to, String param) {

		String result = "";

		Boolean flag = false;
		
		DefaultHttpClient httpclient = null;

		try {

			httpclient = getDefaultHttpClient();

			// 构造请求URL内容

			String timestamp = dateToStr(new Date(), "yyyyMMddHHmmss");// 时间戳

			String sig = ACCOUNTSID + AUTHTOKEN + timestamp;

			String signature = md5Digest(sig);

			String url = new StringBuffer(REST_SERVER).append("/").append(
					VERSION)

			.append("/Accounts/").append(ACCOUNTSID)

			.append("/Messages/templateSMS").append("?sig=")

			.append(signature).toString();

			Map<String, String> map = new HashMap<String, String>();

			map.put("accountSid", ACCOUNTSID);

			map.put("authToken", AUTHTOKEN);

			map.put("appId", APPID);

			map.put("templateId", TEMPLATEID);

			map.put("to", to);

			map.put("param", param);

			String body = new JSONObject(map).toString();
			System.out.println(body);

			body = "{\"templateSMS\":" + body + "}";

			HttpResponse response = get("application/json", ACCOUNTSID,

			AUTHTOKEN, timestamp, url, httpclient, body);

			// 获取响应实体信息

			HttpEntity entity = response.getEntity();

			if (entity != null) {

				result = EntityUtils.toString(entity, "UTF-8");
				int number = result.indexOf("respCode");
				System.out.println(""+number);
				String code = result.substring(number+11,number+17);
				System.out.println(code);
				if ("000000".endsWith(code)) {
					System.out.println("验证码发送成功");
					
					flag = true;
				}
			}
			
			// 确保HTTP响应内容全部被读出或者内容流被关闭

			EntityUtils.consume(entity);
			
		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			// 关闭连接

			if (httpclient != null) {

				httpclient.getConnectionManager().shutdown();

			}

		}
		System.out.println(flag);
		return flag;

	}
	
	
	
	

	private static HttpResponse get(String cType, String accountSid,

	String authToken, String timestamp, String url,

	DefaultHttpClient httpclient, String body) throws Exception {

		HttpPost httppost = new HttpPost(url);

		httppost.setHeader("Accept", cType);//

		httppost.setHeader("Content-Type", cType);

		String src = accountSid + ":" + timestamp;

		String auth = base64Encoder(src);

		httppost.setHeader("Authorization", auth);

		BasicHttpEntity requestBody = new BasicHttpEntity();

		requestBody
				.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));

		requestBody.setContentLength(body.getBytes("UTF-8").length);

		httppost.setEntity(requestBody);

		HttpResponse response = httpclient.execute(httppost);

		return response;

	}

	public static String dateToStr(Date date, String pattern) {

		if (date == null || date.equals(""))

			return null;

		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.format(date);

	}

	public static String md5Digest(String src) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");

		byte[] b = md.digest(src.getBytes(UTF8));

		return byte2HexStr(b);

	}

	public static String base64Encoder(String src) throws Exception {

		BASE64Encoder encoder = new BASE64Encoder();

		return encoder.encode(src.getBytes(UTF8));

	}

	public static String base64Decoder(String dest) throws Exception {

		BASE64Decoder decoder = new BASE64Decoder();

		return new String(decoder.decodeBuffer(dest), UTF8);

	}

	public static String byte2HexStr(byte[] b) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < b.length; i++) {

			String s = Integer.toHexString(b[i] & 0xFF);

			if (s.length() == 1) {

				sb.append("0");

			}

			sb.append(s.toUpperCase());

		}

		return sb.toString();

	}

	public static void main(String[] args) {
//		String templateSMS = PhoneCodeTools.templateSMS("18282241211", "95055201204");
//		System.out.println(templateSMS);
		//System.out.println(Test.templateSMS("15775692034", "1358"));
		String result = "{\"resp\":{\"respCode\":\"000000\",\"templateSMS\":{\"createDate\":\"20160913160643\",\"smsId\":\"6667dd821804afaa28d394576bc64dd3\"}}}";
		int number = result.indexOf("respCode");
		System.out.println(""+number);
		String code = result.substring(number+11,number+17);
		System.out.println(code);
		if ("000000".endsWith(code)) {
			System.out.println("验证码发送成功");
		}
	}

}