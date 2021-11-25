package com.springboot.security.web.service;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class CoolSMSService { 
	
	private final String API_KEY="NCSS4EZCKKW2BKM1";
	private final String API_SECRET="TQFH2HX1HXGNLVFZ5QIASVWBUG72FD5P";
	
	
	public String createAuthenticationCode() {
		String authenticationCode =""; 
		
		Random randNumber = new Random();
		int codeLength=6;
		for(int i =0;i<codeLength ; i++) {
			String randCode = Integer.toString(randNumber.nextInt(10));
			authenticationCode+=randCode;
		}
		return authenticationCode;
	}
	
	public String  sendAuthenticationCode(String phoneNumber) {
		Message coolsms = new Message(API_KEY, API_SECRET);
		String authenticationCode=createAuthenticationCode();
		HashMap<String , String> params= new HashMap<String,String>();
		params.put("to", phoneNumber);//누구에게
		params.put("from","01063998524");//누가 보낼거냐?
		params.put("type", "SMS");
		params.put("text", "jc마켓 가입 인증번호 "+authenticationCode+" 입니다");
		params.put("app_version", "jcmarket app 1.1");
		
		try {
			coolsms.send(params);
		} catch (CoolsmsException e) {
			e.printStackTrace();
		}
		
		return authenticationCode;
	}

}
