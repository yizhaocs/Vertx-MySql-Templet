package com.fuhu;

import java.io.FileOutputStream;
import java.io.IOException;

import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;

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

	public static Logger logger;

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

	protected String getCurServerTime() {
		long TIME = System.currentTimeMillis();
		String TIME_STRING = String.valueOf(TIME);
		String TS = TIME_STRING.substring(0, TIME_STRING.length() - 3);
		return TS;
	}

	protected String generateUuid() {
		return java.util.UUID.randomUUID().toString();
	}

	protected JsonObject rawCommandJsonGenerator(String query) {
		JsonObject rawCommandJson = new JsonObject();
		rawCommandJson.putString("action", "raw");
		rawCommandJson.putString("command", query);
		return rawCommandJson;
	}
}
