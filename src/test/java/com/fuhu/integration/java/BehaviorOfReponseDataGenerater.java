package com.fuhu.integration.java;


public interface BehaviorOfReponseDataGenerater  {
	public SingletonOfConstantsT ct = SingletonOfConstantsT.getInstance();
	public void execute(StatesOfClient state);
}

