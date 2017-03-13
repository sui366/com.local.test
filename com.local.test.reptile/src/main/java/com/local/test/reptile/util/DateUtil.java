package com.local.test.reptile.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;



public class DateUtil {

	private static final int[] DAY_OF_MONTH = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public final static String FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
	public final static String FORMAT_2 = "HH:mm";
	public final static String FORMAT_3 = "HH:mm:ss";
	
	public static Date getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
     * 获取当前时间str，格式yyyyMMddHHmmss
     */
    public static String getCurrentDateTimeStr(){
        return getDateTimeStr(new Date());
    }

    /**
     * 获取当前时间str，格式yyyyMMddHHmmss
     */
    public static String getDateTimeStr(Date date){
    	String timeString = "";
    	if(date != null){
    		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    		timeString = dataFormat.format(date);
    	}
    	
    	return timeString;
    }
    
	/**
	 * 取得指定天数后的时间
	 * 
	 * @param date
	 *            基准时间
	 * @param dayAmount
	 *            指定天数，允许为负数
	 * @return 指定天数后的时间
	 */
	public static Date addDay(Date date, int days) {
		if (date == null)
			return null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.DAY_OF_MONTH, days);

		return gc.getTime();
	}

	public static Date cutMonth(Date date, int month) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.MONTH, -month);

		return gc.getTime();
	}

	/**
	 * 比较两个日期之间的大小
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return boolean 返回值为：如果 结束时间 >= 开始时间 返回true;否则,返回 false
	 */
	public static boolean compareDate(Date startDate, Date endDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null == startDate) {
			startDate = getCurrentTime();
		}
		startDate = sdf.parse(sdf.format(startDate));
		if (null == endDate) {
			endDate = getCurrentTime();
		}
		endDate = sdf.parse(sdf.format(endDate));
		//
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(endDate);
		long time2 = cal.getTimeInMillis();
		long result = time2 - time1;
		//
		boolean result_flag = false;
		if (result >= 0) {
			result_flag = true;
		}
		return result_flag;
	}

	/**
	 * 统计两个日期之间的天数
	 */
	public static int totalDays(Date startDate, Date endDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		startDate = sdf.parse(sdf.format(startDate));
		endDate = sdf.parse(sdf.format(endDate));

		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(endDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));

	}

	/**
	 * 格式化当前时间
	 * 
	 * @param pattern
	 * @return
	 * @author zengdq 2013-5-21
	 */
	public static String formatCurrentTime(String pattern) {
		return format(getCurrentTime(), pattern);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @author zengdq 2013-5-21
	 */
	public static String format(Date date, String pattern) {
		if (date == null || StringUtils.isBlank(pattern)) {
			return "";
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 将时分秒设置为零点
	 * 
	 * @return
	 * @author zengdq 2013-7-8
	 */
	public static Date createZeroTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 得到合法的还款日（大于出账日）
	 * 
	 * @param gmt_post
	 *            出账日
	 * @param gmt_repay
	 *            还款日
	 * @return
	 */
	public static Date getLegalRepayDate(Date gmt_post, Date gmt_repay) {
		// 如果还款日小于出账日(目前差值为0)，则还款日+1月，直到大于出账日
		while (gmt_repay.getTime() - gmt_post.getTime() < 0) {
			gmt_repay = DateUtils.addMonths(gmt_repay, 1);
		}
		return gmt_repay;
	}

	/**
	 * 取得指定小时数后的时间
	 * 
	 * @param date
	 *            基准时间
	 * @param hourAmount
	 *            指定小时数，允许为负数
	 * @return 指定小时数后的时间
	 */
	public static Date addHour(Date date, int hourAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hourAmount);
		return calendar.getTime();
	}

	/**
	 * 取得指定分钟数后的时间
	 * 
	 * @param date
	 *            基准时间
	 * @param minuteAmount
	 *            指定分钟数，允许为负数
	 * @return 指定分钟数后的时间
	 */
	public static Date addMinute(Date date, int minuteAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minuteAmount);
		return calendar.getTime();
	}

	/**
	 * 比较两日期对象中的小时和分钟部分的大小.
	 * 
	 * @param date
	 *            日期对象1, 如果为 <code>null</code> 会以当前时间的日期对象代替
	 * @param anotherDate
	 *            日期对象2, 如果为 <code>null</code> 会以当前时间的日期对象代替
	 * @return 如果日期对象1大于日期对象2, 则返回大于0的数; 反之返回小于0的数; 如果两日期对象相等, 则返回0.
	 */
	public static int compareHourAndMinute(Date date, Date anotherDate) {
		if (date == null) {
			date = new Date();
		}

		if (anotherDate == null) {
			anotherDate = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hourOfDay1 = cal.get(Calendar.HOUR_OF_DAY);
		int minute1 = cal.get(Calendar.MINUTE);

		cal.setTime(anotherDate);
		int hourOfDay2 = cal.get(Calendar.HOUR_OF_DAY);
		int minute2 = cal.get(Calendar.MINUTE);

		if (hourOfDay1 > hourOfDay2) {
			return 1;
		} else if (hourOfDay1 == hourOfDay2) {
			// 小时相等就比较分钟
			return minute1 > minute2 ? 1 : (minute1 == minute2 ? 0 : -1);
		} else {
			return -1;
		}
	}

	/**
	 * 比较两日期对象的大小, 忽略秒, 只精确到分钟.
	 * 
	 * @param date
	 *            日期对象1, 如果为 <code>null</code> 会以当前时间的日期对象代替
	 * @param anotherDate
	 *            日期对象2, 如果为 <code>null</code> 会以当前时间的日期对象代替
	 * @return 如果日期对象1大于日期对象2, 则返回大于0的数; 反之返回小于0的数; 如果两日期对象相等, 则返回0.
	 */
	public static int compareIgnoreSecond(Date date, Date anotherDate) {
		if (date == null) {
			date = new Date();
		}

		if (anotherDate == null) {
			anotherDate = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		date = cal.getTime();

		cal.setTime(anotherDate);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		anotherDate = cal.getTime();

		return date.compareTo(anotherDate);
	}

	/**
	 * 取得当前时间的字符串表示，格式为2006-01-10 20:56:30.756
	 * 
	 * @return 当前时间的字符串表示
	 */
	public static String currentDate2String() {
		return date2String(new Date());
	}

	/**
	 * 取得当前时间的字符串表示，格式为2006-01-10
	 * 
	 * @return 当前时间的字符串表示
	 */
	public static String currentDate2StringByDay() {
		return date2StringByDay(new Date());
	}

	/**
	 * 取得今天的最后一个时刻
	 * 
	 * @return 今天的最后一个时刻
	 */
	public static Date currentEndDate() {
		return getEndDate(new Date());
	}

	/**
	 * 取得今天的第一个时刻
	 * 
	 * @return 今天的第一个时刻
	 */
	public static Date currentStartDate() {
		return getStartDate(new Date());
	}

	/**
	 * 把时间转换成字符串，格式为2006-01-10 20:56:30.756
	 * 
	 * @param date
	 *            时间
	 * @return 时间字符串
	 */
	public static String date2String(Date date) {
		return date2String(date, "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * 按照指定格式把时间转换成字符串，格式的写法类似yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @param date
	 *            时间
	 * @param pattern
	 *            格式
	 * @return 时间字符串
	 */
	public static String date2String(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		return (new SimpleDateFormat(pattern)).format(date);
	}

	/**
	 * 把时间转换成字符串，格式为2006-01-10
	 * 
	 * @param date
	 *            时间
	 * @return 时间字符串
	 */
	public static String date2StringByDay(Date date) {
		return date2String(date, "yyyy-MM-dd");
	}

	/**
	 * 把时间转换成字符串，格式为2006-01-10 20:56
	 * 
	 * @param date
	 *            时间
	 * @return 时间字符串
	 */
	public static String date2StringByMinute(Date date) {
		return date2String(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 把时间转换成字符串，格式为2006-01-10 20:56:30
	 * 
	 * @param date
	 *            时间
	 * @return 时间字符串
	 */
	public static String date2StringBySecond(Date date) {
		return date2String(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 根据某星期几的英文名称来获取该星期几的中文数. <br>
	 * e.g.
	 * <li>monday -> 一</li>
	 * <li>sunday -> 日</li>
	 * 
	 * @param englishWeekName
	 *            星期的英文名称
	 * @return 星期的中文数
	 */
	public static String getChineseWeekNumber(String englishWeekName) {
		if ("monday".equalsIgnoreCase(englishWeekName)) {
			return "一";
		}

		if ("tuesday".equalsIgnoreCase(englishWeekName)) {
			return "二";
		}

		if ("wednesday".equalsIgnoreCase(englishWeekName)) {
			return "三";
		}

		if ("thursday".equalsIgnoreCase(englishWeekName)) {
			return "四";
		}

		if ("friday".equalsIgnoreCase(englishWeekName)) {
			return "五";
		}

		if ("saturday".equalsIgnoreCase(englishWeekName)) {
			return "六";
		}

		if ("sunday".equalsIgnoreCase(englishWeekName)) {
			return "日";
		}

		return null;
	}

	/**
	 * 根据指定的年, 月, 日等参数获取日期对象.
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @return 对应的日期对象
	 */
	public static Date getDate(int year, int month, int date) {
		return getDate(year, month, date, 0, 0);
	}

	/**
	 * 根据指定的年, 月, 日, 时, 分等参数获取日期对象.
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @param hourOfDay
	 *            时(24小时制)
	 * @param minute
	 *            分
	 * @return 对应的日期对象
	 */
	public static Date getDate(int year, int month, int date, int hourOfDay, int minute) {
		return getDate(year, month, date, hourOfDay, minute, 0);
	}

	/**
	 * 根据指定的年, 月, 日, 时, 分, 秒等参数获取日期对象.
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @param hourOfDay
	 *            时(24小时制)
	 * @param minute
	 *            分
	 * @param second
	 *            秒
	 * @return 对应的日期对象
	 */
	public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date, hourOfDay, minute, second);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * 取得某个日期是星期几，星期日是1，依此类推
	 * 
	 * @param date
	 *            日期
	 * @return 星期几
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取某天的结束时间, e.g. 2005-10-01 23:59:59.999
	 * 
	 * @param date
	 *            日期对象
	 * @return 该天的结束时间
	 */
	public static Date getEndDate(Date date) {

		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return cal.getTime();
	}

	/**
	 * 取得一个月最多的天数
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份，0表示1月，依此类推
	 * @return 最多的天数
	 */
	public static int getMaxDayOfMonth(int year, int month) {
		if (month == 1 && isLeapYear(year)) {
			return 29;
		}
		return DAY_OF_MONTH[month];
	}

	/**
	 * 得到指定日期的下一天
	 * 
	 * @param date
	 *            日期对象
	 * @return 同一时间的下一天的日期对象
	 */
	public static Date getNextDay(Date date) {
		return addDay(date, 1);
	}

	/**
	 * 获取某天的起始时间, e.g. 2005-10-01 00:00:00.000
	 * 
	 * @param date
	 *            日期对象
	 * @return 该天的起始时间
	 */
	public static Date getStartDate(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * 根据日期对象来获取日期中的时间(HH:mm:ss).
	 * 
	 * @param date
	 *            日期对象
	 * @return 时间字符串, 格式为: HH:mm:ss
	 */
	public static String getTime(Date date) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 根据日期对象来获取日期中的时间(HH:mm).
	 * 
	 * @param date
	 *            日期对象
	 * @return 时间字符串, 格式为: HH:mm
	 */
	public static String getTimeIgnoreSecond(Date date) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(date);
	}

	/**
	 * 判断是否是闰年
	 * 
	 * @param year
	 *            年份
	 * @return 是true，否则false
	 */
	public static boolean isLeapYear(int year) {
		Calendar calendar = Calendar.getInstance();
		return ((GregorianCalendar) calendar).isLeapYear(year);
	}

	/**
	 * 取得一年中的第几周。
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取上周的指定星期的日期。
	 * 
	 * @param dayOfWeek
	 *            星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
	 */
	public static Date getDateOfPreviousWeek(int dayOfWeek) {
		if (dayOfWeek > 7 || dayOfWeek < 1) {
			throw new IllegalArgumentException("参数必须是1-7之间的数字");
		}

		return getDateOfRange(dayOfWeek, -7);
	}

	/**
	 * 获取本周的指定星期的日期。
	 * 
	 * @param dayOfWeek
	 *            星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
	 */
	public static Date getDateOfCurrentWeek(int dayOfWeek) {
		if (dayOfWeek > 7 || dayOfWeek < 1) {
			throw new IllegalArgumentException("参数必须是1-7之间的数字");
		}

		return getDateOfRange(dayOfWeek, 0);
	}

	/**
	 * 获取下周的指定星期的日期。
	 * 
	 * @param dayOfWeek
	 *            星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
	 */
	public static Date getDateOfNextWeek(int dayOfWeek) {
		if (dayOfWeek > 7 || dayOfWeek < 1) {
			throw new IllegalArgumentException("参数必须是1-7之间的数字");
		}

		return getDateOfRange(dayOfWeek, 7);
	}

	private static Date getDateOfRange(int dayOfWeek, int dayOfRange) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + dayOfRange);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 计算两个日期之间相差的天时分秒
	 */
	public static String calcDuration(Date startDate, Date endDate) {
		if(startDate == null || endDate == null) return "";
		long betweenTime = endDate.getTime() - startDate.getTime();
		if(betweenTime<0){
			return "0天0时0分0秒";
		}
		long seconds = betweenTime / 1000;
		
		StringBuilder result = new StringBuilder();
		// 计算天
		long days = seconds / (3600 * 24);
		if (days > 0) {
			result.append(days).append("天");
		}
		// 计算小时
		long seconds2 = seconds % (3600 * 24);
		long hours = seconds2 / 3600;
		if (hours > 0) {
			result.append(hours).append("时");
		}
		// 计算分钟
		long seconds3 = seconds2 % 3600;
		long minutes = seconds3 / 60;
		if (minutes > 0) {
			result.append(minutes).append("分");
		}
		// 计算秒
		long seconds4 = seconds3 % 60;
		if (seconds4 > 0) {
			result.append(seconds4).append("秒");
		}
		return result.toString();
	}
	
	/**
	 * 计算两个日期之间相差的天时分
	 */
	public static String calcDurationEndMinute(Date startDate, Date endDate) {
		if(startDate == null || endDate == null) return "";
		long betweenTime = Math.abs(endDate.getTime() - startDate.getTime());
		long seconds = betweenTime / 1000;
		
		StringBuilder result = new StringBuilder();
		// 计算天
		long days = seconds / (3600 * 24);
		if (days > 0) {
			result.append(days).append("天");
		}
		// 计算小时
		long seconds2 = seconds % (3600 * 24);
		long hours = seconds2 / 3600;
		if (hours > 0) {
			result.append(hours).append("时");
		}
		// 计算分钟
		long seconds3 = seconds2 % 3600;
		long minutes = seconds3 / 60;
		if (minutes > 0) {
			result.append(minutes).append("分");
		}
		return result.toString();
	}
	
	/**
	 * 根据类型返回查询起始时间(0:不限,1:今天,2:最近一周,3：最近一月,4：最近三月,5：最近六月,6：最近一年)
	 * @Title: getSearchDate
	 * @Description: 
	 * @Return: Date
	 * @Throws:
	 * 时间要从00:00:00开始
	 */
	@SuppressWarnings("deprecation")
	public static Date getSearchDate(String time) {
		if (StringUtils.equals("1", time)) {
			Date date = new Date();
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			return date;
		} else if (StringUtils.equals("2", time)) {
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_YEAR, -7);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();
		} else if (StringUtils.equals("3", time)) {
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.MONDAY, -1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();
		} else if (StringUtils.equals("4", time)) {
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.MONDAY, -3);
			return calendar.getTime();
		} else if (StringUtils.equals("5", time)) {
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.MONDAY, -6);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();
		} else if (StringUtils.equals("6", time)) {
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.YEAR, -1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();
		}
		return null;
	}
	/**
	 * String 转 date
	 */
	public static Date StringToDate(String dateStr, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);  
	    Date date = new Date();
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
		}  
		return date;
	}
}