package webserver.game.impl;

import io.javalin.Javalin;
import io.javalin.http.Context;
import webserver.AbstractController;
import webserver.game.GameApi;
import webserver.game.GameController;
import webserver.utils.Filters;

public class GameControllerImpl extends AbstractController implements GameController {
    public GameControllerImpl(String path) {
        super(path);
    }

    private GameApi getApi(Context context) {
        return GameApi.of(getBattleshipInstance(context));
    }

    @Override
    public void registerRoutes(Javalin app) {
        app.before(path("*"), Filters.ensureClientAcceptsMimeType("application", "json"));
        //here the registered route. (see lab6 to understand what it does)
    }
}
