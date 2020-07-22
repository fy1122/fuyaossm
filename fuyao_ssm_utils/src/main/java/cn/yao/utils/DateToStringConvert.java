package cn.yao.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToStringConvert implements Converter<Date,String> {
    @Override
    public String convert(Date source) {
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:ss");
        return format.format(source);
    }
}
