package com.yizhao.integration.java;

import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitOfPerPackageAndUser extends SingletonOfSuperClient {
	@BeforeClass
	public static void setUpClass() throws Exception {

	}

	@Test
	public void _A1_STATE_PER_PACKAGE_AND_USER__INSERT() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_AND_USER_INSERT);
	}

	@Test
	public void _A2_STATE_PER_PACKAGE_UPDATE() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_AND_USER_UPDATE);
	}

	@Test
	public void _A3_STATE_PER_PACKAGE_AND_USER_GET() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_AND_USER_GET);
	}

	@Test
	public void _A4_STATE_PER_PACKAGE_AND_USER_DELETE() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_AND_USER_DELETE);
	}
}
