//package com.example.projectdemo.util.tool;
//
//
//import android.content.Context;
//
//import com.sunshion.MainActivity;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.params.CoreConnectionPNames;
//import org.apache.http.protocol.HTTP;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import javax.net.ssl.HttpsURLConnection;
//
//public class HttpUtil {
//
//	/**
//	 * AJAXPOST提交
//	 *
//	 */
//	public static String post(String url, Map<String, String> params) {
//		if(MainActivity.MAIN_THREAD_ID == Thread.currentThread().getId()){
//			System.out.println(MainActivity.MAIN_THREAD_ID + "----线程ID1：" + Thread.currentThread().getId());
//			new Exception("在主线程里面调用网络请求").printStackTrace();
//		}
//		String rs ="";
//		DefaultHttpClient client = null; //new DefaultHttpClient();
//
//		try {
//			if(url.startsWith("https://")){
//				client = HttpsUtil.getHttpsClient();
//			}else{
//				client = new DefaultHttpClient();
//			}
//			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 12 * 1000);
//			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 12 * 1000);
//
//			HttpPost post = new HttpPost(url);
//			post.setHeader("User-Agent", "SunsungWebview/0.0 -->post");
//			StringBuffer sb = new StringBuffer();
//			if (params != null) {
//				Iterator<String> keys = params.keySet().iterator();
//				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//				while (keys.hasNext()) {
//					String key = keys.next();
//					nvps.add(new BasicNameValuePair(key, (String)params.get(key)));
//				}
//				post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
//			}
//			HttpResponse resp = client.execute(post);
//			HttpEntity entity = resp.getEntity();
//			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), HTTP.UTF_8));
//
//			String result = br.readLine();
//			while (result != null) {
//				sb.append(result);
//				result = br.readLine();
//			}
//			rs =sb.toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return rs;
//	}
//
//	//请求服务器，返回JSON对象
//	public static JSONObject AjaxPostObject(String url, JSONObject pm) {
//		JSONObject rs = new JSONObject();
//		DefaultHttpClient client = new DefaultHttpClient();
//
//		HttpPost post = new HttpPost(url);
//		StringBuffer sb = new StringBuffer();
//		try {
//			StringEntity reqEntity = new StringEntity(pm.toString(),HTTP.UTF_8);
//			reqEntity.setContentType("application/x-www-form-urlencoded");
//			post.setEntity(reqEntity);
//			HttpResponse resp = client.execute(post);
//
//			HttpEntity entity = resp.getEntity();
//			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), HTTP.UTF_8));
//
//			String result = br.readLine();
//			while (result != null) {
//				sb.append(result);
//				result = br.readLine();
//			}
//			String jsonstr=StringUtil.isNull(sb.toString(),"").trim();
//			rs = new JSONObject(jsonstr);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return rs;
//	}
//
//	/**
//	 * 上传图片
//	 */
//	public static String uploadPicture(String filePath, String params, Context context) {
//		String res = "";
//		HttpURLConnection conn = null;
//		String BOUNDARY = "-----------------" + CodeGenerator.getUUID(); // boundary就是request头和上传文件内容的分隔符
//		try {
//			URL url = new URL(Globals.WEB_URL + "/web/plugins/sys/admin/bz/upload.xp?doAction=uploadPicture" + params);
//			//conn = (HttpURLConnection) url.openConnection();
//			if (Globals.WEB_URL.startsWith("https://")) {
//				HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
//				https.setHostnameVerifier(HttpsUtil.SunHostnameVerifier);
//				conn = https;
//			} else {
//				conn = (HttpURLConnection)url.openConnection();
//			}
//			conn.setConnectTimeout(1000 * 60);
//			conn.setReadTimeout(30000);
//			conn.setDoOutput(true);
//			conn.setDoInput(true);
//			conn.setUseCaches(false);
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Connection", "Keep-Alive");
//			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
//			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
//
//			OutputStream out = new DataOutputStream(conn.getOutputStream());
//
//			// file
//			File file = new File(filePath);
//			String filename = file.getName();
//
//			StringBuffer strBuf = new StringBuffer();
//			strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
//			strBuf.append("Content-Disposition: form-data; name=\"uploadfile\"; filename=\"" + filename + "\"\r\n");
//			strBuf.append("Content-Type: image/jpg\r\n\r\n");
//
//			out.write(strBuf.toString().getBytes());
//
//			DataInputStream in = new DataInputStream(new FileInputStream(file));
//			int bytes = 0;
//			byte[] bufferOut = new byte[1024];
//			while ((bytes = in.read(bufferOut)) != -1) {
//				out.write(bufferOut, 0, bytes);
//			}
//			in.close();
//
//			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
//			out.write(endData);
//			out.flush();
//			out.close();
//
//			// 读取返回数据
//			strBuf = new StringBuffer();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(
//					conn.getInputStream()));
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				strBuf.append(line).append("\n");
//			}
//			res = strBuf.toString();
//
//			reader.close();
//			reader = null;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				conn.disconnect();
//				conn = null;
//			}
//		}
//		return res;
//	}
//
//}
