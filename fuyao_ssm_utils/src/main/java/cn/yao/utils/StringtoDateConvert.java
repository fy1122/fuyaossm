package cn.yao.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringtoDateConvert implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (source==null){
            throw new RuntimeException("请输入数据");
        }
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d=null;
        try {
             d= format.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return d;
    }
}
