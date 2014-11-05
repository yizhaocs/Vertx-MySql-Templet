package com.fuhu.integration.java;

public class FactoryOfCurlCommandsSetter {
	public BehaviorOfCurlCommandsSetter createSetter(StatesOfClient state) {
		BehaviorOfCurlCommandsSetter ccs = null;
		if (state.toString().indexOf("USER") >= 0) {
			ccs = new CurlCommandsSetterOfPerPackage();
		} else  {
			ccs = new CurlCommandsSetterOfPerPackage();
		}
		return ccs;
	}
}
