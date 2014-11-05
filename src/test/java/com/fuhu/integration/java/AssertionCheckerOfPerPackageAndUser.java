package com.fuhu.integration.java;

public class AssertionCheckerOfPerPackageAndUser implements BehaviorOfAssertionChecker {

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_PER_PACKAGE_AND_USER_INSERT:
			break;
		case STATE_PER_PACKAGE_AND_USER_UPDATE:
			break;
		case STATE_PER_PACKAGE_AND_USER_GET_1:
			break;
		case STATE_PER_PACKAGE_AND_USER_DELETE_1:
			break;
		default:
		}
	}

}
