package com.hs.common.tool;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import jodd.util.Base64;

public class SHAUtil {


	/**'
	 * 
	 * @param secret
	 * @param message
	 * @param sha HmacSHA256
	 * @return
	 */
	public static String encode(String secret ,   String message , String sha) {
		String hash=null;
		try {

			Mac mac = Mac.getInstance(sha);
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), sha);
			mac.init(secret_key);

			hash = Base64.encodeToString(mac.doFinal(message.getBytes()));
			
			
		}
		catch (Exception e){
			System.out.println("Error");
		}

		return hash;
	}



}
