package com.yubin.homework.web.utils;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

public class DateUtil {

    /**
     * string 转date
     *
     * @param dateStr
     * @return
     */
    public static Date string2Date(String dateStr) {
        return DateTime.parse(dateStr, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
    }
}