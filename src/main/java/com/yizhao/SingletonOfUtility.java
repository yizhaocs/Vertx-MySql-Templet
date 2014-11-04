package com.yizhao;

import java.io.FileOutputStream;
import java.io.IOException;

public class SingletonOfUtility {
	/* Setup for Singleton pattern */
	private static SingletonOfUtility instance = null;

	private SingletonOfUtility() {

	}

	public static SingletonOfUtility getInstance() {
		if (instance == null) {
			instance = new SingletonOfUtility();
		}
		return instance;
	}

	protected void byteArrayToFile(byte[] byteArray) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("/Users/yizhao/Desktop/abc.png");
			out.write(byteArray);
		} catch (IOException e) {
			System.out.println("Caught IOException: " + e.getMessage());
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println("Caught IOException: " + e.getMessage());
				}
			}
		}
	}

	protected String byteArrayToHexString(byte[] byteArray) {
		char[] hexArray = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[byteArray.length * 2];
		for (int i = 0; i < byteArray.length; i++) {
			int v = byteArray[i] & 0xFF;
			hexChars[i * 2] = hexArray[v >>> 4];
			hexChars[i * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
}
