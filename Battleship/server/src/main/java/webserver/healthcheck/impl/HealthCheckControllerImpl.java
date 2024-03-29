package webserver.healthcheck.impl;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import webserver.AbstractController;
import webserver.healthcheck.HealthCheckApi;
import webserver.healthcheck.HealthCheckController;
import webserver.utils.Filters;

public class HealthCheckControllerImpl extends AbstractController implements HealthCheckController {
    public HealthCheckControllerImpl(String path) {
        super(path);
    }

    private HealthCheckApi getApi(Context context) {
        return HealthCheckApi.of(getBattleshipInstance(context));
    }

    @Override
    public void registerRoutes(Javalin app) {
        app.before(path("*"), Filters.ensureClientAcceptsMimeType("application", "json"));
        app.get(path("/"), this::getHealth);
    }


    @Override
    public void getHealth(Context context) throws HttpResponseException {
        HealthCheckApi api = getApi(context);

        var futureResult = api.getHealth();
        asyncReplyWithBody(context, "application/json", futureResult);
    }
}