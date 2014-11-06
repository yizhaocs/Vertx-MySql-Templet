package com.fuhu;

import java.util.Map;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

public class QueryGeneratorOfUpsert  implements BehaviorOfQueryGenerator{

	@Override
	public String execute(StatesOfServer state, JsonObject response, HttpServerRequest bridge_between_server_and_client,Buffer curlBody, String currentTime) {
		switch (state) {
		case STATE_PER_PACKAGE_UPSERT:
		case STATE_PER_PACKAGE_AND_USER_UPSERT:
			String userKey = state.equals(StatesOfServer.STATE_PER_PACKAGE_AND_USER_UPSERT) ? bridge_between_server_and_client.params().get("userKey") : null;
			String packageName = bridge_between_server_and_client.params().get("packageName");
			String streamKey = bridge_between_server_and_client.params().get("streamKey");
			// StringBuilder binaryString = new StringBuilder(bytesToHex(curlBody.getBytes()));
			String hex = utility.byteArrayToHexString(curlBody.getBytes());
			String[] insertColumnsWithoutUserKey = { cs.perPackageAndUser_TableColumns[1], cs.perPackageAndUser_TableColumns[2], cs.perPackageAndUser_TableColumns[3], cs.perPackageAndUser_TableColumns[4] };
			String[] valuesWithoutUserKey = { "'" + packageName + "'", "'" + streamKey + "'", "X'" + hex + "'", currentTime };
			String[] insertColumnsWithUserKey = { cs.perPackageAndUser_TableColumns[0], cs.perPackageAndUser_TableColumns[1], cs.perPackageAndUser_TableColumns[2], cs.perPackageAndUser_TableColumns[3], cs.perPackageAndUser_TableColumns[4] };
			String[] valuesWithUserKey = { "'" + userKey + "'", "'" + packageName + "'", "'" + streamKey + "'", "X'" + hex + "'", currentTime };
			String[] updateColumns = { cs.perPackageAndUser_TableColumns[3], cs.perPackageAndUser_TableColumns[4] };
			String queryResult = state.equals(StatesOfServer.STATE_PER_PACKAGE_AND_USER_UPSERT) ? queryGenerator.upsert(cs.tableName, insertColumnsWithUserKey, valuesWithUserKey, updateColumns) : queryGenerator.upsert(cs.tableName, insertColumnsWithoutUserKey, valuesWithoutUserKey,
					updateColumns);
			return queryResult;
		default:
			return null;
		}
		
	}

}
