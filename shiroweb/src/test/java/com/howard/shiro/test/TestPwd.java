package com.howard.shiro.test;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class TestPwd {
	public static void main(String[] args) {
		String algorithmName = "SHA-256";  
		String username = "admin";  
		String password = "123456";  
		String salt1 = username;  
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();  
		System.out.println(salt2);
		
//	    DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512  
//	    hashService.setHashAlgorithmName("SHA-256");  
//	    hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐，默认无  
//	    hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false  
//	    hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个  
//	    hashService.setHashIterations(1); //生成Hash值的迭代次数  
//	      
//	    HashRequest request = new HashRequest.Builder()  
//	                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))  
//	                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();  
//	    String hex = hashService.computeHash(request).toHex();   
		System.out.println(salt1 + salt2);
		int hashIterations = 2;  
		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);  
		String encodedPassword = hash.toHex();   
		System.out.println(encodedPassword);
	}
}
