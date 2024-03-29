package webserver.healthcheck;

import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import webserver.Controller;
import webserver.healthcheck.impl.HealthCheckControllerImpl;

public interface HealthCheckController extends Controller {

    static HealthCheckController of(String root) {
        return new HealthCheckControllerImpl(root);
    }


    void getHealth(Context context) throws HttpResponseException;
}
