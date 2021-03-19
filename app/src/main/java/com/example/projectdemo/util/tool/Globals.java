package com.example.projectdemo.util.tool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Globals {
//	public static String IP = "xsgj.ipower.ah.cn";
	public static String IP = "112.30.200.69"; //外勤通正式 //"120.209.139.46" "112.30.212.69"
	public static String WEB_PORT = "9443";
//	public static String IP = "120.209.139.43"; //合肥测试


	public static String WEB_NAME = "xszs";
	public static String SXPUSH_PORT = "9998";//消息推送接口  在SxPushThread类里对120.209.139.46进行了修正
	public static boolean isToolenValidate = true;
//	public static String WEB_URL = String.format("http://%s:%s/%s", IP, WEB_PORT, WEB_NAME);

	public static String WEB_URL = String.format("https://%s:%s/%s", IP, WEB_PORT, WEB_NAME);
	public static String RTMP_URL = String.format("rtmp://%s:5080/webcam/%s", IP, WEB_NAME);

	public static String LOCAL_PAGE_PATH = "";//本地页面存放路径
	public static boolean debug = true;

//	public static Map<String, WebViewPage> webViewActivityMap = new Hashtable<String, WebViewPage>();
//	public static List<WebViewPage> webViewActivityList = new ArrayList<WebViewPage>();
//	public static List<RelativeView> relativeViewList = new ArrayList<RelativeView>();
	/**记录错误日志*/
	public static void log(String remarks, Throwable tr, Context context){
		Log.w(remarks, tr);
		
		//将错误栈转化成字符
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		tr.printStackTrace(printWriter);
		Throwable cause = tr.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String logContent = writer.toString();
		//插入数据库
//		insertLogToDB(remarks, logContent, context);
	}
	/**记录错误日志*/
	public static void log(String remarks, String logContent, Context context){
		Log.w(remarks, logContent);
//		insertLogToDB(remarks, logContent, context);
	}
	/**将日志插入数据库*/
//	public static void insertLogToDB(String remarks, String logContent, Context context){
//		synchronized (DBOperator.synObj) {
//			SQLiteDatabase db = DBOperator.getDatabase(context);
//			StringBuffer sqlBuffer = new StringBuffer();
//			sqlBuffer.append(" INSERT INTO t_app_errorlog (groupid,userid,logid, logtime,logcontent,remarks) ");
//			sqlBuffer.append("      VALUES (?, ?, ?, ?, ?,?) ");
//			db.execSQL(sqlBuffer.toString(), new Object[]{
//				UserInfo.getGroupid(),UserInfo.getUserid(),CodeGenerator.getBh(), DateUtil.MMddYYYY_HHmmss.format(DateT.getCurrentTimestamp()),logContent,remarks
//			});
//			db.close();
//		}
//		CoreService.activeTimer("UploadThread");
//	}
	/**根据APPID取服务URL*/
	public static void getWebUrl(Context context){
		
	}
}
