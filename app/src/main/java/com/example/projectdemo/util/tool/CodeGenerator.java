package com.example.projectdemo.util.tool;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public class CodeGenerator {
	/**
	 * 产生编号时所使用的流水号，由getBh()函数调用
	 */
	public static int serialNumber = 0;
	public static int serialNumberYYYYMMDD = 0;
	/**
	 * 产生编号同步线程使用，由getBh()函数调用
	 */
	private static final Object synObj = new Object();
	/**
	 * @FunName: getNumberFormat
	 * @Description : 获取指定位数为bit的数字格式器
	 * @param bit
	 * @return NumberFormat返回数字格式器；
	 */
	public static NumberFormat getNumberFormat(int bit) {
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMinimumIntegerDigits(bit);
		formatter.setMaximumIntegerDigits(bit);
		formatter.setGroupingUsed(false);
		return formatter;
	}

	/**
	 * @FunName: getCurrentTimeString
	 * @Description : 获取当前时间字符串，精确到毫秒
	 * @return String�?返回当前时间字符串；
	 */
	public static String getCurrentTimeString() {

		NumberFormat formatter2 = getNumberFormat(2);
		NumberFormat formatter3 = getNumberFormat(3);
		NumberFormat formatter4 = getNumberFormat(4);

		Calendar Cld = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
		StringBuffer sb = new StringBuffer();

		sb.append(formatter4.format(Cld.get(Calendar.YEAR)));
		sb.append(formatter2.format(Cld.get(Calendar.MONTH) + 1));
		sb.append(formatter2.format(Cld.get(Calendar.DATE)));
		sb.append(formatter2.format(Cld.get(Calendar.HOUR_OF_DAY)));
		sb.append(formatter2.format(Cld.get(Calendar.MINUTE)));
		sb.append(formatter2.format(Cld.get(Calendar.SECOND)));
		sb.append(formatter3.format(Cld.get(Calendar.MILLISECOND)));

		return sb.toString();
	}
	/**
	 * 获取年月日格式
	 * @return
	 * @author WLY
	 * @日期 2014-9-14 下午3:42:19
	 */
	public static String getCurrentTimeYYYYMMDD() {

		NumberFormat formatter2 = getNumberFormat(2);

		Calendar Cld = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
		StringBuffer sb = new StringBuffer();

		sb.append(formatter2.format(Cld.get(Calendar.MONTH) + 1));
		sb.append(formatter2.format(Cld.get(Calendar.DATE)));
		return sb.toString();
	}

	/**
	 * 获取年月日加三位流水号
	 * @return
	 * @author WLY
	 * @日期 2014-9-14 下午3:40:43
	 */
	public static String getBhMMDD() {
		long temp;
		synchronized (synObj) {
			temp = serialNumberYYYYMMDD++;
			if (serialNumberYYYYMMDD == 1000) {// 流水号从0-999循环
				serialNumberYYYYMMDD = 0;
			}
		}
		return getCurrentTimeYYYYMMDD() + getNumberFormat(3).format(temp);
	}

	/**
	 * @FunName: getBh
	 * @Description : 获取编号
	 * @return String 返回当前自动生成的编号；
	 */
	public static String getBh() {
		long temp;
		synchronized (synObj) {
			temp = serialNumber++;
			if (serialNumber == 1000) {// 流水号从0-999循环
				serialNumber = 0;
			}
		}
		return getCurrentTimeString() + getNumberFormat(3).format(temp);
	}
	/**获取UUID*/
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
