package com.cpeoc.androiddevsearch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-23 10:02
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public class DateUtil {
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static long dateToStamp(String time) throws ParseException {
        SimpleDateFormat sdr = new SimpleDateFormat(FORMAT_YMDHMS, Locale.CHINA);
        Date date = sdr.parse(time);
        return date.getTime();
    }

    public static String stampToDate(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat(FORMAT_YMDHMS, Locale.CHINA);
        Date date = new Date(time);
        return sdr.format(date);
    }
}
