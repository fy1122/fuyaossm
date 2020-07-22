package cn.yao.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncodesUtils {
    @Autowired
    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static  String encodingPassword(String password){
        return  bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        System.out.println(encodingPassword("1233"));
    }
}
