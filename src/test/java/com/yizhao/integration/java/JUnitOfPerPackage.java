package com.yizhao.integration.java;

import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitOfPerPackage extends SingletonOfSuperClient {
	@BeforeClass
	public static void setUpClass() throws Exception {

	}

	@Test
	public void _A1_STATE_PER_PACKAGE_INSERT() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_INSERT);
	}

	@Test
	public void _A1_STATE_PER_PACKAGE_UPDATE() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_UPDATE);
	}

	@Test
	public void _A1_STATE_PER_PACKAGE_GET() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_GET);
	}

	@Test
	public void _A1_STATE_PER_PACKAGE_DELETE() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_DELETE);
	}
}
