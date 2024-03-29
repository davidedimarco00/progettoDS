package webserver.healthcheck;

import common.Battleship;
import common.HealthCheck;
import webserver.healthcheck.impl.HealthCheckApiImpl;

import java.util.concurrent.CompletableFuture;

public interface HealthCheckApi {
    static HealthCheckApi of(Battleship storage) {
        return new HealthCheckApiImpl(storage);
    }

    CompletableFuture<HealthCheck> getHealth();
}
