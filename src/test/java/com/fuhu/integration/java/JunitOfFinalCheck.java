package com.fuhu.integration.java;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;
import org.vertx.testtools.VertxAssert;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JunitOfFinalCheck extends SuperClient {
	@Test
	public void _H1_check() {
		SuperClient.setState(StatesOfClient.STATE_LAST_ASSERTION_FOR_CHECKING_THE_CLIENT_RUN_OVER_ALL_STATES);
		ct.mapStates.put(curState, true);
		boolean allStatesAsserted = true;
		for (StatesOfClient s : StatesOfClient.values()) {
			if (s != StatesOfClient.STATE_RESET) {
				if (ct.mapStates.get(s) == false) {
					allStatesAsserted = false;
					System.out.println("_H1_check Failed at the State of:" + s.toString());
					break;
				}
			}
		}

		assertTrue(allStatesAsserted);
		VertxAssert.testComplete();
	}
}
