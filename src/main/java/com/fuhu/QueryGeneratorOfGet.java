package com.fuhu;

import java.util.Map;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

public class QueryGeneratorOfGet  implements BehaviorOfQueryGenerator{

	@Override
	public String execute(StatesOfServer state, JsonObject response, HttpServerRequest bridge_between_server_and_client,Buffer curlBody, String currentTime) {
		switch (state) {
		case STATE_PER_PACKAGE_GET:
		case STATE_PER_PACKAGE_AND_USER_GET:
			String userKey = state.equals(StatesOfServer.STATE_PER_PACKAGE_AND_USER_GET) ? bridge_between_server_and_client.params().get("userKey") : null;
			String packageName = bridge_between_server_and_client.params().get("packageName");
			String streamKey = bridge_between_server_and_client.params().get("streamKey");
			String ts = bridge_between_server_and_client.params().get(cs.TS);

			String[] whereClauseCoulmns = { cs.perPackageAndUser_TableColumns[0], cs.perPackageAndUser_TableColumns[1], cs.perPackageAndUser_TableColumns[2], cs.perPackageAndUser_TableColumns[4] };
			String[] whereClauseOperator = { "=", "=", "=", "<" };
			String[] whereClauseValues = { state.equals(StatesOfServer.STATE_PER_PACKAGE_AND_USER_GET) ? "'" + userKey + "'" : "'\"" + "\"'", "'" + packageName + "'", "'" + streamKey + "'", ts };
			String queryResult = queryGenerator.select(cs.perPackageAndUser_TableColumns[3], cs.tableName, whereClauseCoulmns, whereClauseOperator, whereClauseValues);
			return queryResult;
		default:
			return null;
		}
	}

}
