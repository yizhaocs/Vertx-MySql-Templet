package com.fuhu;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServerRequest;

public abstract class StrategyPatternOfParentAPI extends MainServerVerticle {
	BehaviorOfQueryGenerator mBehaviorOfQueryGenerator = null;
	BehaviorOfDatabaseResponseProcessor mBehaviorOfProcessSendResponse = null;

	public StrategyPatternOfParentAPI() {

	}

	abstract void execute(final StatesOfServer state, final Vertx vertx, final HttpServerRequest bridge_between_server_and_client);

	public void setBehaviorOfQueryGenerator(BehaviorOfQueryGenerator b) {
		mBehaviorOfQueryGenerator = b;
	}

	public void setBehaviorOfDatabaseResponseProcessor(BehaviorOfDatabaseResponseProcessor b) {
		mBehaviorOfProcessSendResponse = b;
	}
}
