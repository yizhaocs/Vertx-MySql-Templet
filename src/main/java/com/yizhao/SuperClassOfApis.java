package com.yizhao;

public class SuperClassOfApis extends MainVerticle {
	SingletonOfQueryGenerator qg = SingletonOfQueryGenerator.getInstance();

	protected String getCurServerTime() {
		long TIME = System.currentTimeMillis();
		String TIME_STRING = String.valueOf(TIME);
		String TS = TIME_STRING.substring(0, TIME_STRING.length() - 3);
		return TS;
	}

	protected String generateUuid() {
		return java.util.UUID.randomUUID().toString();
	}
}
