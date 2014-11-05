package com.fuhu.integration.java;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitOfPerPackageAndUser extends SuperClient {
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
