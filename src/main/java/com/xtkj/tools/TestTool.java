package com.xtkj.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigInteger;
import java.security.MessageDigest;


public enum TestTool {

    INSTALL;

    public String getMD5String(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> String toGsonString(T t){
        GsonResult<T> gsonResult = new GsonResult();
        gsonResult.setStateCode(200);
        gsonResult.setMsg("ok");
        gsonResult.setObj(t);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String s = gson.toJson(gsonResult);
        return s;
    }
    
    public Object getBeanFactory(String beanId) {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		return context.getBean(beanId);
	}
}
