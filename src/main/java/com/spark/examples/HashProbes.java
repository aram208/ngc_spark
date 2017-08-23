package com.spark.examples;

import org.apache.commons.codec.digest.HmacUtils;

public class HashProbes {

	public static void main(String[] args){
		
		HashProbes hp = new HashProbes();
		hp.runHashing();
		
	}
	
	public void runHashing(){
		
		System.out.println("----------------MD5------------------");
		System.out.println(HmacUtils.hmacMd5Hex("CCG", "0937"));
		System.out.println(HmacUtils.hmacMd5Hex("0937", "CCG"));
		System.out.println("----------------SHA1------------------");
		System.out.println(HmacUtils.hmacSha1Hex("CCG", "0937"));
		System.out.println(HmacUtils.hmacSha1Hex("0937", "CCG"));
		System.out.println("-----------------SHA256---------------");
		System.out.println(HmacUtils.hmacSha256Hex("CCG", "0937"));
		System.out.println(HmacUtils.hmacSha256Hex("0937", "CCG"));
	}
	
}
