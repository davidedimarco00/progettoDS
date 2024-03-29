package webserver.players.impl;

import common.Player;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import webserver.AbstractController;
import webserver.players.PlayerApi;
import webserver.players.PlayerController;
import webserver.utils.Filters;


public class PlayerControllerImpl extends AbstractController implements PlayerController {
    public PlayerControllerImpl(String path) {
        super(path);
    }

    private PlayerApi getApi(Context context) {
        return PlayerApi.of(getBattleshipInstance(context));
    }

    @Override
    public void registerRoutes(Javalin app) {
        app.before(path("*"), Filters.ensureClientAcceptsMimeType("application", "json"));
        app.post(path("/"), this::postPlayer);
        app.get(path("/"), this::getPlayers);
    }

    @Override
    public void postPlayer(Context context) throws HttpResponseException {
        PlayerApi api = getApi(context);
        var player = context.bodyAsClass(Player.class);
        var futureResult = api.connectPlayer(player);
        asyncReplyWithoutBody(context, "application/json", futureResult);
        //asyncReplyWithBody(context, "text/plain", futureResult);
    }

    @Override
    public void getPlayers(Context context) throws HttpResponseException {
        PlayerApi api = getApi(context);

        var futureResult = api.getPlayers();
        asyncReplyWithBody(context, "application/json", futureResult);
    }
}