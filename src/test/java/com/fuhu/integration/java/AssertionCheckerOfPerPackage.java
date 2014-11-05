package com.fuhu.integration.java;

public class AssertionCheckerOfPerPackage implements BehaviorOfAssertionChecker {

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_PER_PACKAGE_INSERT:
			break;
		case STATE_PER_PACKAGE_UPDATE:
			break;
		case STATE_PER_PACKAGE_GET_1:
			break;
		case STATE_PER_PACKAGE_DELETE_1:
			break;
		default:
		}
	}

}
