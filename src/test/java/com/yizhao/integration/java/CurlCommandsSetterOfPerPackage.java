package com.yizhao.integration.java;

public class CurlCommandsSetterOfPerPackage extends BehaviorOfCurlCommandsSetter{

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_PER_PACKAGE_INSERT:
			break;
		case STATE_PER_PACKAGE_UPDATE:
			break;
		case STATE_PER_PACKAGE_GET:
			break;
		case STATE_PER_PACKAGE_DELETE:
			break;
		default:
			pmfc.printCurrentStateInfo();
		}
	}

}
