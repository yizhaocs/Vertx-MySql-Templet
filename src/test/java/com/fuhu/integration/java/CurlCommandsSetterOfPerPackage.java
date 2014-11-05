package com.fuhu.integration.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.vertx.java.core.buffer.Buffer;

public class CurlCommandsSetterOfPerPackage extends BehaviorOfCurlCommandsSetter {

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_PER_PACKAGE_INSERT:
		case STATE_PER_PACKAGE_AND_USER_INSERT:
			currentRequest = ct.PUT_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE;
			dataSetup();
			break;
		case STATE_PER_PACKAGE_UPDATE:
		case STATE_PER_PACKAGE_AND_USER_UPDATE:
			currentRequest = ct.PUT_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE;
			dataSetup();
			break;
		case STATE_PER_PACKAGE_GET:
		case STATE_PER_PACKAGE_AND_USER_GET:
			currentRequest = ct.GET_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE;
			currentDataSendToServer = null;
			break;
		case STATE_PER_PACKAGE_DELETE:
		case STATE_PER_PACKAGE_AND_USER_DELETE:
			currentRequest = ct.DELETE_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE;
			currentDataSendToServer = null;
			break;
		default:
			pmfc.printCurrentStateInfo();
		}
	}

	private void dataSetup() {
		byte[] byteArray = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream("src/test/resources/testing.png");
			try {
				byteArray = IOUtils.toByteArray(in);
				currentDataSendToServer = new Buffer(byteArray);
				System.out.println("Arrays.toString(IOUtils.toByteArray(imageInFile)):" + Arrays.toString(byteArray));
				System.out.println("currentDataSendToServer:" + Arrays.toString(currentDataSendToServer.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Caught FileNotFoundException: " + e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
