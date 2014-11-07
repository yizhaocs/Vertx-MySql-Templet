package com.fuhu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpClientRequest;
import org.vertx.java.core.http.HttpServerFileUpload;
import org.vertx.java.core.json.JsonObject;

public class SingletonOfPrintingMethodsOfServer {
	/* Setup for Singleton pattern */
	private static SingletonOfPrintingMethodsOfServer instance = null;
	
	private SingletonOfPrintingMethodsOfServer() {

	}

	public static SingletonOfPrintingMethodsOfServer getInstance() {
		if (instance == null) {
			instance = new SingletonOfPrintingMethodsOfServer();
		}
		return instance;
	}
	
	/* Variables*/
	private SingletonOfSwitchesOfServer sos = SingletonOfSwitchesOfServer.getInstance();
	private SingletonOfConstantsS scs = SingletonOfConstantsS.getInstance();
	SingletonOfUtility utility = SingletonOfUtility.getInstance();
	// utility.logger utility.logger = container.utility.logger();

	protected void printServerTime(StatesOfServer state) {
		if (sos.isTesting_Print_Switch()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("{-----------------Server Time:" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + scs.HEADER_END);
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printHeaders(StatesOfServer state, List<Entry<String, String>> header) {
		printServerTime(state);
		if (sos.isTesting_Print_Switch()) {
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("printHeaders:" + header.toString());
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printCurlBody(StatesOfServer state,Buffer body) {
		if (sos.isTesting_Print_Switch()) {
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("curlBodyChecker:" + body.toString());
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printRawCommandJson(StatesOfServer state, JsonObject raw) {
		if (sos.isTesting_Print_Switch()) {
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("printRawCommandJson:" + raw.encodePrettily());
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}
	
	protected void printTransactionJson(StatesOfServer state, JsonObject raw) {
		if (sos.isTesting_Print_Switch()) {
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("printTransactionJson:" + raw.encodePrettily());
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printWhichStateIsTesting(StatesOfServer state) {
		if (sos.isTesting_Print_Switch()) {
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("HTTP Server is performing " + state + " .");
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printDatabaseMessage(StatesOfServer state, JsonObject databaseMessageBody) {
		if (sos.isTesting_Print_Switch()) {
			System.out.println(state);
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("HTTP Server on state: " + state + ", it just " + "recieved response from database.");
			utility.logger.info("printDatabaseMessage:");
			utility.logger.info(databaseMessageBody.encodePrettily());
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printQuery(StatesOfServer state, String query) {
		if (sos.isTesting_Print_Switch()) {
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("printQuery:" + query);
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printCurlData(StatesOfServer state, String body) {
		if (sos.isTesting_Print_Switch()) {
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("printCurlData:" + body);
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printDBresponseFromOSGSessionVerificationAPI(StatesOfServer state, JsonObject j) {
		if (sos.isTesting_Print_Switch()) {
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("printDBresponseFromOSGSessionVerificationAPI:" + j.encodePrettily());
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printResponseToClient(StatesOfServer state, JsonObject response) {
		if (sos.isTesting_Print_Switch()) {
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("printResponseToClient: " + response.encodePrettily());
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printOSGsessionCheckHeaders(StatesOfServer state, HttpClientRequest requestSendFromClienttoServer) {
		if (sos.isTesting_Print_Switch()) {
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("printOSGsessionCheckHeaders: " + requestSendFromClienttoServer.headers().entries().toString());
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printOSGsessionCheckResponse(StatesOfServer state, Buffer body) {
		if (sos.isTesting_Print_Switch()) {
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			if (body != null) {
				utility.logger.info("printOSGsessionCheckResponse: " + body.toString());
			} else {
				utility.logger.info("printOSGsessionCheckResponse: " + "body is null");
			}

			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printMultiPartInfo(StatesOfServer state, HttpServerFileUpload upload, Buffer mainBuffer) {
		if (sos.isTesting_Print_Switch()) {
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("got here too. upload.size() = " + upload.size());
			utility.logger.info("endHandler() called");
			utility.logger.info("mainBuffer = " + mainBuffer.length());
			utility.logger.info("upload.contentType() = " + upload.contentType());
			utility.logger.info("upload.name() = " + upload.name());
			utility.logger.info("upload.filename() = " + upload.filename());
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printExceptionHandlerTraceStack(StatesOfServer state, Throwable t) {
		if (sos.isTesting_Print_Switch()) {
			utility.logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			utility.logger.info("printExceptionHandlerTraceStack: ");
			t.printStackTrace();
			utility.logger.info(scs.END_SMALL);
			utility.logger.info(" ");
		}
	}

	protected void printOSGconfigInfo(JsonObject osgConfig) {
		if (sos.isTesting_Print_Switch()) {
			System.out.println("Print OSG Config Info:");
			System.out.println(osgConfig.encode());
		}
	}

	protected void printDBconfigInfo(JsonObject dbConfig) {
		if (sos.isTesting_Print_Switch()) {
			System.out.println("Print DB Config Info:");
			System.out.println(dbConfig.encode());
		}
	}
}
