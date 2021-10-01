package com.kh.common.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper {

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String key) {

		String value = "";
		
		if (key != null && (key.equals("userPwd") || key.equals("newPwd"))) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				
				// 전달받은 비밀번호를 바이트 배열로 받아주기
				byte[] bytes = super.getParameter(key).getBytes(Charset.forName("UTF-8"));
				
				// md 객체에 변환한 바이트 배열을 전달하여 갱신
				md.update(bytes);
				
				// java.util.Base64 인코더를 이용하여 암호화된 바이트 배열을 인코딩 과정을 통해 문자열로 출력
				value = Base64.getEncoder().encodeToString(md.digest());
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} // SHA-512 방식으로 암호화 진행
		} else {
			value = super.getParameter(key);
		}
		
		return value;
	}

}
