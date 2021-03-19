package com.example.projectdemo.util.tool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Decoder.BASE64Encoder;

public class MTInfoUtil {

	//获得手机序列号
	public static String getSerialNumber(){
		String serial = null;
		try {
				Class<?> c = Class.forName("android.os.SystemProperties");
				Method get = c.getMethod("get", String.class);
				serial = (String) get.invoke(c, "ro.serialno");
			}
		catch (Exception ignored) {

		}
		return serial.trim();
	}

	// 获得手机品牌
	public static String getBrand() {
		return android.os.Build.BRAND.trim();
	}

	// 获得手机型号
	public static String getModel() {
		return android.os.Build.MODEL.trim();
	}

	// 获得硬件版本号
	public static String getHardwareVersion() {
		return android.os.Build.VERSION.RELEASE.trim();
	}

	// 获得android版本
	@SuppressWarnings("deprecation")
	public static String getAndroidVersion() {
		return android.os.Build.VERSION.SDK.trim();
	}

	// 获得基带版本
	public static String getBaseVersion() {
		return android.os.Build.VERSION.INCREMENTAL.trim();
	}

	// 获得内核版本
	public static String getKernelVersion() {
		String str1 = "/proc/version";
		String str2 = "";
		try {
			FileReader localFileReader = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(
					localFileReader, 8192);
			str2 = localBufferedReader.readLine();
			String[] arrayOfString = str2.split("\\s+");
			str2 = arrayOfString[2];// KernelVersion
			localBufferedReader.close();
		} catch (IOException e) {
		}
		return str2.trim();
	}

	// 获得内部版本
	public static String getInternalVersion() {
		String ver = "" ;
		if(android.os.Build.DISPLAY .contains(android.os.Build.VERSION.INCREMENTAL)){
			ver = android.os.Build.DISPLAY;
		}else{
			ver = android.os.Build.VERSION.INCREMENTAL;
		}
		return ver.trim();
	}
	//获得Rom版本
	public static String getRomVersion() {
		return android.os.Build.ID.trim();
	}
	//是否为双模
	public static String isDoubleMode(Context mContext) {
		String imei1=getIMEI1(mContext);
		String imei2=getIMEI2(mContext);
		if((!imei1.equals(""))&&(!imei2.equals(""))){
			return "Yes".trim();
		}
		return "No".trim();
	}

	private static class GeminiMethodNotFoundException extends Exception {
		private static final long serialVersionUID = -3241033488141442594L;

		public GeminiMethodNotFoundException(String info) {
			super(info);
		}
	}

	private static String getOperatorBySlot(Context context,
                                            String predictedMethodName, int slotID)
			throws GeminiMethodNotFoundException {
		String inumeric = null;
		TelephonyManager telephony = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		try {
			Class<?> telephonyClass = Class.forName(telephony.getClass()
					.getName());
			Class<?>[] parameter = new Class[1];
			parameter[0] = int.class;
			Method getSimID = telephonyClass.getMethod(predictedMethodName,
					parameter);
			Object[] obParameter = new Object[1];
			obParameter[0] = slotID;
			Object ob_phone = getSimID.invoke(telephony, obParameter);
			if (ob_phone != null) {
				inumeric = ob_phone.toString();
			}
		} catch (Exception e) {
			throw new GeminiMethodNotFoundException(predictedMethodName);
		}
		return inumeric;
	}
	//获得IMEI1
	public static String getIMEI1(Context mContext) {
		TelephonyManager telephonyManager = ((TelephonyManager) mContext
				.getSystemService(Context.TELEPHONY_SERVICE));
		@SuppressLint("MissingPermission") String imeiSIM1 = telephonyManager.getDeviceId();
		try {
			imeiSIM1 = getOperatorBySlot(mContext, "getDeviceIdGemini", 0);
		} catch (GeminiMethodNotFoundException e) {
			try {
				imeiSIM1 = getOperatorBySlot(mContext, "getDeviceId", 0);
			} catch (GeminiMethodNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		if (null == imeiSIM1) {
			imeiSIM1 = "";
		}
		return imeiSIM1.trim();
	}
	//获得IMEI2
	public static String getIMEI2(Context mContext) {
		String imeiSIM2 ="";
		try {
			imeiSIM2 = getOperatorBySlot(mContext, "getDeviceIdGemini", 1);
		} catch (GeminiMethodNotFoundException e) {
			try {
				imeiSIM2 = getOperatorBySlot(mContext, "getDeviceId", 1);
			} catch (GeminiMethodNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		if (null == imeiSIM2) {
			imeiSIM2 = "";
		}
		return imeiSIM2.trim();
	}
	//获得RAM
	public static String getRunMemory(Context mContext) {
		long mTotal;
		// /proc/meminfo读出的内核信息进行解释
		String path = "/proc/meminfo";
		String content = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path), 8);
			String line;
			if ((line = br.readLine()) != null) {
				content = line;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// beginIndex
		int begin = content.indexOf(':');
		// endIndex
		int end = content.indexOf('k');
		// 截取字符串信息
		content = content.substring(begin + 1, end).trim();
		mTotal = Integer.parseInt(content);
		return (mTotal + "").trim();
	}

	// 获取内部存储空间
	@SuppressWarnings("deprecation")
	public static String getInternalSpace() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		float a=(float)(totalBlocks * blockSize);
		float b=(float) (1024 * 1024*1024);
		 String innerSpace=a/b+"";
		 int index=innerSpace.indexOf(".");
			if(index>-1){
				innerSpace=innerSpace.substring(0, index+2);
			};
		 return innerSpace.trim();
	}

	// 获取外部存储空间
	public static String getExternalSpace(Context context) {
		StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
		String externalSpace="";
		try {
			Class<?>[] paramClasses = {};
			Method getVolumePathsMethod = StorageManager.class.getMethod("getVolumePaths", paramClasses);
			getVolumePathsMethod.setAccessible(true);
			Object[] params = {};
			Object invoke = getVolumePathsMethod.invoke(storageManager, params);
			for (int i = 0; i < ((String[])invoke).length; i++) {
				StatFs stat = getStatFs(((String[])invoke)[i]);
				if(i==1){
					@SuppressWarnings("deprecation")
					long blockSize = stat.getBlockSize();
					@SuppressWarnings("deprecation")
					long totalBlocks = stat.getBlockCount();
					float a=(float)(totalBlocks * blockSize);
					float b=(float)(1024 * 1024*1024);
					externalSpace=a/b+"";
					int index=externalSpace.indexOf(".");
					if(index>-1){
						externalSpace=externalSpace.substring(0, index+2);
					};

				}
			}
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
			return externalSpace.trim();
	}

	private static StatFs getStatFs(String path) {
		try {
			return new StatFs(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//获得分辨率
	public static String getWidthHeight(Context context) {
		// 在service中也能得到高和宽
		WindowManager mWindowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		@SuppressWarnings("deprecation")
		int width = mWindowManager.getDefaultDisplay().getWidth();
		@SuppressWarnings("deprecation")
		int height = mWindowManager.getDefaultDisplay().getHeight();
		String wh = width + "*" + height;
		return wh.trim();
	}
	//获得cpu型号
	public static String getCPU() {
		/*String str1 = "/proc/cpuinfo";
		String str2 = "";
		String[] cpuInfo = { "", "" }; // 1-cpu型号 //2-cpu频率
		String[] arrayOfString;
		try {
			FileReader fr = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
			str2 = localBufferedReader.readLine();
			arrayOfString = str2.split("\\s+");
			for (int i = 2; i < arrayOfString.length; i++) {
				cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
			}
			str2 = localBufferedReader.readLine();
			arrayOfString = str2.split("\\s+");
			cpuInfo[1] += arrayOfString[2];
			localBufferedReader.close();
		} catch (IOException e) {
		}
		return cpuInfo[0]+cpuInfo[1];*/
		return android.os.Build.CPU_ABI.trim() ;
	}

	//获得终端id
	public static String getMtid(Context mContext){
		String tempMtid=getIMEI1(mContext).trim()+getIMEI2(mContext).trim()+getSerialNumber().trim();
		String mTid="";
		try {
			mTid=EncoderByMd5(tempMtid);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return mTid.trim();
	}

	//对字符串进行加密
	 public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	 	//确定计算方法
		MessageDigest md5= MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		//加密后的字符串
		String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr.trim();
	 }

	@SuppressLint("MissingPermission")
	public static String getIccid1(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getSimSerialNumber();  //取出ICCID;
			
	}
	
	public static String getIccid2(){
		return "";
	}
	
	@SuppressLint("MissingPermission")
	public static String getImsi1(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getSubscriberId();     //取出IMSI
	}
	public static String getImsi2(){
		return "";
	}
}
