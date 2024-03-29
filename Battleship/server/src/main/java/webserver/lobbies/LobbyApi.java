package webserver.lobbies;

import common.Battleship;
import common.Lobby;
import webserver.lobbies.impl.LobbyApiImpl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LobbyApi {

    static LobbyApi of(Battleship storage) {
        return new LobbyApiImpl(storage);
    }

    CompletableFuture<List<Lobby>> getLobbies();

    CompletableFuture<Void> postLobby(Lobby lobby);

    CompletableFuture<Void> addPlayerToLobby(Integer lobby, String player);

    CompletableFuture<Void> removePlayerFromLobby(Integer lobbyId, String username);

    CompletableFuture<Lobby> getLobbyById(Integer lobbyId);
}
