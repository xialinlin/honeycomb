package com.hs.common.tool;

import java.util.UUID;

import com.sohu.idcenter.SidWorker;

import de.rtner.security.auth.spi.SimplePBKDF2;

public class StringUtil {



	// pbk加密算法的盐池
	private final static String PBK_SALT= "94_SALT_KEY_ABC%82?";


	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString(); 
		return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);

	}


	/**
	 * 使用twitter snowflake 获取唯一性ID
	 * @return
	 */
	public static String sId() {
		return Long.toString(SidWorker.nextSid());
	}


	/**
	 * 对密码进行加密
	 * @param passwd
	 * @return
	 */
	public static String encode(String passwd) {
		String s = passwd+"{"+PBK_SALT+"}";
		return  new SimplePBKDF2().deriveKeyFormatted(s);
	}


	/**
	 * 
	 * @param formatted
	 * @param passwd
	 * @return
	 */
	public static boolean decode(String formatted , String passwd) {
		String s = passwd+"{"+PBK_SALT+"}";
		return new SimplePBKDF2().verifyKeyFormatted(formatted, s);
	}

}
