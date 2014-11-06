package com.fuhu.integration.java;

public class FactoryOfAssertionChecker {
	public BehaviorOfAssertionChecker createChecker(StatesOfClient state) {
		BehaviorOfAssertionChecker f = null;
		if (state.toString().indexOf("STATUS_CHECK") < 0) {
			f = new AssertionCheckerOfNotStatusErrorCheck();
		} else {
			f = new AssertionCheckerOfStatusErrorCheck();
		}
		return f;
	}
}
