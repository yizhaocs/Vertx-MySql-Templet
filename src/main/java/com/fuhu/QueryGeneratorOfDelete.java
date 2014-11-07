package com.fuhu;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class QueryGeneratorOfDelete implements BehaviorOfQueryGenerator {

	@Override
	public String execute(StatesOfServer state, JsonObject response, HttpServerRequest bridge_between_server_and_client,Buffer curlBody, String currentTime) {
		switch (state) {
		case STATE_PER_PACKAGE_DELETE:
		case STATE_PER_PACKAGE_AND_USER_DELETE:
			String userKey = state.equals(StatesOfServer.STATE_PER_PACKAGE_AND_USER_DELETE) ? bridge_between_server_and_client.params().get("userKey") : null;
			String packageName = bridge_between_server_and_client.params().get("packageName");
			String streamKey = bridge_between_server_and_client.params().get("streamKey");
			String[] whereClauseCoulmns = { cs.perPackageAndUser_TableColumns[0], cs.perPackageAndUser_TableColumns[1], cs.perPackageAndUser_TableColumns[2] };
			String[] whereClauseOperator = { "=", "=", "=" };
			String[] whereClauseValues = { state.equals(StatesOfServer.STATE_PER_PACKAGE_AND_USER_DELETE) ? "'" + userKey + "'" : "'\"" + "\"'", "'" + packageName + "'", "'" + streamKey + "'" };
			String queryResult = queryGenerator.delete(cs.tableName, whereClauseCoulmns, whereClauseOperator, whereClauseValues);
			return queryResult;
		default:
			return null;
		}

	}

}
