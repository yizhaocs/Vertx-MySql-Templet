package com.fuhu.integration.java;

public class AssertionCheckerOfPerPackageAndUser implements BehaviorOfAssertionChecker {

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_PER_PACKAGE_AND_USER_INSERT:
			break;
		case STATE_PER_PACKAGE_AND_USER_UPDATE:
			break;
		case STATE_PER_PACKAGE_AND_USER_GET:
			break;
		case STATE_PER_PACKAGE_AND_USER_DELETE:
			break;
		default:
		}
	}

}
