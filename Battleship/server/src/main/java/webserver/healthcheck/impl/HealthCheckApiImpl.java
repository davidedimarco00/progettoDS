package webserver.healthcheck.impl;

import common.Battleship;
import common.HealthCheck;
import webserver.AbstractApi;
import webserver.healthcheck.HealthCheckApi;

import java.util.concurrent.CompletableFuture;

public class HealthCheckApiImpl extends AbstractApi implements HealthCheckApi {

    public HealthCheckApiImpl(Battleship storage) {
        super(storage);
    }

    @Override
    public CompletableFuture<HealthCheck> getHealth() {
        return null;
    }
}
