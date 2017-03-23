package cn.fulldroid.lib.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	public static String getTimeDesc(Date create_date) {
		long startT = new Date().getTime(); // 现在时间
		long endT = create_date.getTime(); // 创建时间
		long ss = (startT - endT) / (1000); // 共计秒数

		int MM = (int) ss / 60; // 共计分钟数
		int hh = (int) ss / 3600; // 共计小时数
		int dd = (int) hh / 24; // 共计天数

		if (MM == 0) {
			return "刚刚";
		} else if (MM < 60 && MM != 0) {
			return String.valueOf(MM) + "分钟前";
		} else if (MM >= 60 && hh < 24) {
			return String.valueOf(hh) + "小时前";
		} else if (hh >= 24 && dd <= 31) {
			return String.valueOf(dd) + "天前";
		} else if (dd > 31 && dd < 62) {
			return "一个月以前";
		} else {
			return "很久以前";
		}
	}


	public static String getTimeDesc(String create_date) {
		return getTimeDesc(StrToDate(create_date,"yyyy-MM-dd HH:mm:ss"));
	}


	public static String formatDateByType(Date date, int type) {
		if (date != null) {
			String fmStr = "";
			switch (type) {
				case 1:
					fmStr = "yyyy年MM月dd日 HH时mm分ss秒";
					break;
				case 2:
					fmStr = "yyyy/MM/dd HH:mm";
					break;
				case 3:
					fmStr = "yyyy-MM-dd HH:mm:ss";
					break;
				case 4:
					fmStr = "yyyy年MM月dd日";
					break;
				case 5:
					fmStr = "yyyy-MM-dd";
					break;
				case 6:
					fmStr = "yyyy年MM月dd日HH时";
					break;
				case 7:
					fmStr = "HH:mm";
					break;
				case 8:
					fmStr = "yyyy年MM月";
					break;

			}
			SimpleDateFormat sdf = new SimpleDateFormat(fmStr);

			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 字符串转换成日期
	 *
	 * @param str
	 * @param pattern
	 * @return date
	 */
	public static Date StrToDate(String str,String pattern) {

		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 字符串转换成日期
	 *
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 时间格式化，转换是标准 yyyy-MM-dd HH:mm:ss ，主要是把单数转换成两位前面加0
	 *
	 * @param dateStr
	 * @return
	 */
	public static String dateFormat(String dateStr) {

		String res = "";
		String[] arr = dateStr.split(" ");
		if (arr.length != 2) {
			Log.v("CommonUtil", "时间格式错误，缺少空格");
			res = "";
		} else {
			String[] dateArr = arr[0].split("-");
			if (dateArr.length != 3) {
				Log.v("CommonUtil", "时间格式错误，缺少‘-’ ");
				res = "";
			} else {
				if (dateArr[0].length() != 4) {
					Log.v("CommonUtil", "时间格式错误，year格式错误（yyyy） ");
					res = "";
				} else {
					res = dateArr[0] + "-" + numberFormat(dateArr[1]) + "-" + numberFormat(dateArr[2]);
					String[] timeArr = arr[1].split(":");
					if (timeArr.length != 3) {
						Log.v("CommonUtil", "时间格式错误，缺少‘:’ ");
						res = "";
					} else {
						res = res + " " + numberFormat(timeArr[0]) + ":" + numberFormat(timeArr[1]) + ":"
								+ numberFormat(timeArr[2]);
					}
				}
			}
		}

		return res;
	}

	/**
	 * 时间数字，单数时前面加0
	 *
	 * @return
	 */
	public static String numberFormat(String time) {
		if (time.length() == 1) {
			time = "0" + time;
		}
		return time;
	}
}
