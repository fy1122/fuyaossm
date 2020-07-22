package cn.yao.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static  String dateToString(Date date,String partten){
        DateFormat format=new SimpleDateFormat(partten);
       return format.format(date);
    }
    public static Date StringtoDate(String str,String partten) throws ParseException {
        DateFormat format=new SimpleDateFormat(partten);
        return format.parse(str);
    }

}
