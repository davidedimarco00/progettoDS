package webserver.battlefield.impl;

import common.Battlefield;
import common.Lobby;
import common.PlayerShip;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import webserver.AbstractController;
import webserver.battlefield.BattlefieldApi;
import webserver.battlefield.BattlefieldController;
import webserver.lobbies.LobbyApi;
import webserver.utils.Filters;

public class BattlefieldControllerImpl extends AbstractController implements BattlefieldController {
    public BattlefieldControllerImpl(String path) {
        super(path);
    }

    private BattlefieldApi getApi(Context context) {
        return BattlefieldApi.of(getBattleshipInstance(context));
    }

    @Override
    public void registerRoutes(Javalin app) {
        app.before(path("*"), Filters.ensureClientAcceptsMimeType("application", "json"));
        app.post(path("/"), this::postPlayerShips);
        app.get(path("/"), this::getBattlefields);
        app.get(path("/{battlefieldId}"), this::getBattlefieldById);
        app.get(path("/lobby/{lobbyId}"), this::getBattlefieldByLobbyId);
    }

    @Override
    public void getBattlefields(Context context) throws HttpResponseException {
        BattlefieldApi api = getApi(context);

        var futureResult = api.getBattlefields();
        asyncReplyWithBody(context, "application/json", futureResult);
    }

    @Override
    public void getBattlefieldById(Context context) throws HttpResponseException {
        BattlefieldApi api = getApi(context);

        var lobbyId = context.pathParam("{battlefieldId}");
        var futureResult = api.getBattlefieldById(Integer.valueOf(lobbyId));
        asyncReplyWithBody(context, "application/json", futureResult);

    }

    @Override
    public void postPlayerShips(Context context) throws HttpResponseException {
        BattlefieldApi api = getApi(context);

        var content = context.bodyAsClass(PlayerShip.class);
        var futureResult = api.addShipToBattlefield(content);
        asyncReplyWithBody(context, "application/json", futureResult);

    }

    @Override
    public void getBattlefieldByLobbyId(Context context) throws HttpResponseException {
        BattlefieldApi api = getApi(context);

        var lobbyId = context.pathParam("{lobbyId}");
        var futureResult = api.getBattlefieldByLobbyId(Integer.valueOf(lobbyId));
        asyncReplyWithBody(context, "application/json", futureResult);

    }
}