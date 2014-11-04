package com.yizhao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.mina.util.Base64;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;


public class ApiOfGet extends SuperClassOfApis {

	public ApiOfGet() {

	}

	public void execute(StatesOfServer state, final Vertx vertx, final HttpServerRequest bridge_between_server_and_client) {
		
		String packageName = bridge_between_server_and_client.params().get("packageName");
		String streamKey = bridge_between_server_and_client.params().get("streamKey");
		String[] whereClauseCoulmns = { cs.perPackageAndUser_TableColumns[0], cs.perPackageAndUser_TableColumns[1], cs.perPackageAndUser_TableColumns[2]};
		String[] whereClauseValues = { "'\"" + "\"'", "\"" + packageName + "\"", "\"" + streamKey + "\"" };

		String queryResult = qg.select(cs.perPackageAndUser_TableColumns[3], cs.tableName, whereClauseCoulmns, whereClauseValues);
		System.out.println("query:" + queryResult);
		JsonObject rawCommandJson = new JsonObject();
		rawCommandJson.putString("action", "raw");
		rawCommandJson.putString("command", queryResult);
		System.out.println("rawCommandJson:" + rawCommandJson.encodePrettily());
		vertx.eventBus().send("backend", rawCommandJson, new Handler<Message<JsonObject>>() {
			/*
			 * This handler recieves response from MySql DBMS
			 */
			@Override
			public void handle(Message<JsonObject> databaseMessage) {
				Buffer responseBuffer = new Buffer();
				JsonObject databaseMessageBody = databaseMessage.body();
				JsonArray databaseMessageResults = databaseMessageBody.getArray("results");
				JsonArray results = databaseMessageResults.get(0);
				JsonArray binaryDataArray = results.get(0);
				//byte[] binaryData = results.get(0).getBytes();
				JsonObject response = new JsonObject();
				response.putString("status", "okay");
				response.putArray("binaryData", binaryDataArray);
				
//				try {
//					//String base64String = Base64.encodeBase64String(binaryDataArray);
//					//readIMAGEFileThenCopyIMAGEFile(Base64.decodeBase64(base64String));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				bridge_between_server_and_client.response().end(response.encodePrettily());
			}
		});
	}
	
	String toBinary( byte[] bytes )
	{
	    StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
	    for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
	        sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
	    return sb.toString();
	}
	
	public  void readIMAGEFileThenCopyIMAGEFile(byte[] b) throws IOException {
		//FileInputStream in = null;
		ByteArrayInputStream in = new ByteArrayInputStream(b);
		FileOutputStream out = null;
		try {
			//in = new FileInputStream("C:\\Users\\Yi\\Desktop\\inputSource.jpg");
			// When you're working with FileOutputStream you don't have to create the file first,
			// you can simply start writing to it.
			out = new FileOutputStream("C:\\Users\\Yi\\Desktop\\outputSource.jpg");
			int i;
			while ((i = in.read()) != -1) {
				out.write(i);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Caught FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Caught IOException: " + e.getMessage());
		} finally {
	
			if (out != null) {
				out.close();
			}
		}
	}
}
