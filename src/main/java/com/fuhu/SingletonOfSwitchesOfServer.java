package com.fuhu;

public class SingletonOfSwitchesOfServer {
	/* Setup for Singleton pattern */
	private static SingletonOfSwitchesOfServer instance = null;

	private SingletonOfSwitchesOfServer() {

	}

	public static SingletonOfSwitchesOfServer getInstance() {
		if (instance == null) {
			instance = new SingletonOfSwitchesOfServer();
		}
		return instance;
	}

	private final boolean Testing_Print_SWITCH = true;
	private final boolean STATUS_DESCRIPTION_SWITCH = true;
	private final boolean IS_DEBUT = true;

	protected boolean isTesting_Print_Switch() {
		return Testing_Print_SWITCH;
	}

	protected boolean isStatusDescriptionSwitch() {
		return STATUS_DESCRIPTION_SWITCH;
	}

	protected boolean isDubug() {
		return IS_DEBUT;
	}
}
