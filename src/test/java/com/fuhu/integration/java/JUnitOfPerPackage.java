package com.fuhu.integration.java;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitOfPerPackage extends SuperClient {
	@BeforeClass
	public static void setUpClass() throws Exception {

	}

	@Test
	public void _A1_STATE_PER_PACKAGE_INSERT() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_INSERT);
	}

	@Test
	public void _A2_STATE_PER_PACKAGE_GET_1() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_GET_1);
	}

	@Test
	public void _B1_STATE_PER_PACKAGE_DELETE_1() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_DELETE_1);
	}

	@Test
	public void _B2_STATE_PER_PACKAGE_GET_2() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_GET_2);
	}

	@Test
	public void _C1_STATE_PER_PACKAGE_UPDATE() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_UPDATE);
	}

	@Test
	public void _C2_STATE_PER_PACKAGE_GET_3() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_GET_3);
	}

	@Test
	public void _D1_STATE_PER_PACKAGE_DELETE_2() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_DELETE_2);
	}

	@Test
	public void _D2_STATE_PER_PACKAGE_GET_4() {
		sendRequest(StatesOfClient.STATE_PER_PACKAGE_GET_4);
	}
}
