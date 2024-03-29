package webserver.lobbies.impl;

import common.Lobby;
import common.Player;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import webserver.AbstractController;
import webserver.lobbies.LobbyApi;
import webserver.lobbies.LobbyController;
import webserver.utils.Filters;

public class LobbyControllerImpl extends AbstractController implements LobbyController {
    public LobbyControllerImpl(String path) {
        super(path);
    }

    private LobbyApi getApi(Context context) {
        return LobbyApi.of(getBattleshipInstance(context));
    }

    @Override
    public void registerRoutes(Javalin app) {
        app.before(path("*"), Filters.ensureClientAcceptsMimeType("application", "json"));
        app.get(path("/"), this::getLobbies);
        app.post(path("/"), this::postLobby);
        app.put(path("/{lobbyId}"), this::putPlayerInLobby);
        app.get(path("/{lobbyId}"), this::getLobbyById);
        app.put(path("/remove/{lobbyId}"), this::putOutPlayerFromLobby);

    }


    @Override
    public void getLobbies(Context context) throws HttpResponseException {
        LobbyApi api = getApi(context);

        var futureResult = api.getLobbies();
        asyncReplyWithBody(context, "application/json", futureResult);
    }

    @Override
    public void getLobbyById(Context context) throws HttpResponseException {
        LobbyApi api = getApi(context);

        var lobbyId = context.pathParam("{lobbyId}");
        var futureResult = api.getLobbyById(Integer.valueOf(lobbyId));
        asyncReplyWithBody(context, "application/json", futureResult);
    }

    @Override
    public void postLobby(Context context) throws HttpResponseException {
        LobbyApi api = getApi(context);

        var lobby = context.bodyAsClass(Lobby.class);
        var futureResult = api.postLobby(lobby);
        asyncReplyWithoutBody(context, "application/json", futureResult);
    }

    @Override
    public void putPlayerInLobby(Context context) throws HttpResponseException {
        LobbyApi api = getApi(context);

        var lobbyId = context.pathParam("{lobbyId}");
        var player = context.bodyAsClass(Player.class);
        var futureResult = api.addPlayerToLobby(Integer.valueOf(lobbyId), player.getUsername());
        asyncReplyWithoutBody(context, "application/json", futureResult);
    }

    @Override
    public void putOutPlayerFromLobby(Context context) throws HttpResponseException {
        LobbyApi api = getApi(context);

        var lobbyId = context.pathParam("{lobbyId}");
        var player = context.bodyAsClass(Player.class);
        var futureResult = api.removePlayerFromLobby(Integer.valueOf(lobbyId), player.getUsername());
        asyncReplyWithoutBody(context, "application/json", futureResult);

    }
}
