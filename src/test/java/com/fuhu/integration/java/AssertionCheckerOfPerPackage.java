package com.fuhu.integration.java;

public class AssertionCheckerOfPerPackage implements BehaviorOfAssertionChecker {

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		/* Check for insert is correct */
		case STATE_PER_PACKAGE_INSERT_1:
			break;
		case STATE_PER_PACKAGE_GET_1:
			break;
		/* Check for get is correct */
		case STATE_PER_PACKAGE_GET_2:
			break;
		/* Check for delete is correct */
		case STATE_PER_PACKAGE_DELETE_1:
			break;
		case STATE_PER_PACKAGE_GET_3:
			break;
		/* Check for update is correct */
		case STATE_PER_PACKAGE_INSERT_2:
			break;
		case STATE_PER_PACKAGE_UPDATE:
			break;
		case STATE_PER_PACKAGE_GET_4:
			break;
		/* Clean up everything then check for all data are cleaned */
		case STATE_PER_PACKAGE_DELETE_2:
			break;
		case STATE_PER_PACKAGE_GET_5:
			break;
		default:
		}
	}
}
