package com.fuhu.integration.java;

public class FactoryOfAssertionChecker {
	public BehaviorOfAssertionChecker createChecker(StatesOfClient state) {
		BehaviorOfAssertionChecker f = null;
		if (state.toString().indexOf("USER") >= 0) {
			f = new AssertionCheckerOfPerPackage();
		} else {
			f = new AssertionCheckerOfPerPackage();
		}
		return f;
	}
}
