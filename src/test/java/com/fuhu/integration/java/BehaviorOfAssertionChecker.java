package com.fuhu.integration.java;

public interface BehaviorOfAssertionChecker {
	public SingletonOfConstantsT ct = SingletonOfConstantsT.getInstance();

	public void execute(StatesOfClient state);

}
