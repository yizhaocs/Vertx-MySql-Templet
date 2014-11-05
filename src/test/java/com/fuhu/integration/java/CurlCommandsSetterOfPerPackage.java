package com.fuhu.integration.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.vertx.java.core.buffer.Buffer;

public class CurlCommandsSetterOfPerPackage extends BehaviorOfCurlCommandsSetter {

	@Override
	public void execute(StatesOfClient state) {
		switch (state) {
		case STATE_PER_PACKAGE_INSERT:
			currentRequest = ct.PUT_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE;
			File imageFile = new File("src/test/resources/testing.png");
			try {
				// Reading a Image file from file system
				FileInputStream imageInFile = new FileInputStream(imageFile);
				byte imageData[] = new byte[(int) imageFile.length()];
				imageInFile.read(imageData);
				currentDataSendToServer = new Buffer(imageData);
			} catch (FileNotFoundException e) {
				System.out.println("Image not found" + e);
			} catch (IOException ioe) {
				System.out.println("Exception while reading the Image " + ioe);
			}
			break;
		case STATE_PER_PACKAGE_UPDATE:
			currentRequest = ct.PUT_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE;
			currentDataSendToServer = null;
			break;
		case STATE_PER_PACKAGE_GET:
			currentRequest = ct.GET_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE;
			currentDataSendToServer = null;
			break;
		case STATE_PER_PACKAGE_DELETE:
			currentRequest = ct.DELETE_REQUEST;
			currentPath = ct.PATH_OF_PER_PACKAGE;
			currentDataSendToServer = null;
			break;
		default:
			pmfc.printCurrentStateInfo();
		}
	}

}
