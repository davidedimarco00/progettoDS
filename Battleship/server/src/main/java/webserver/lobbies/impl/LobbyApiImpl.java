package webserver.lobbies.impl;

import common.Battleship;
import common.Lobby;
import common.exception.ConflictException;
import common.exception.MissingException;
import io.javalin.http.ConflictResponse;
import io.javalin.http.NotFoundResponse;
import webserver.AbstractApi;
import webserver.lobbies.LobbyApi;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LobbyApiImpl extends AbstractApi implements LobbyApi {

    public LobbyApiImpl(Battleship storage) {
        super(storage);
    }

    @Override
    public CompletableFuture<List<Lobby>> getLobbies() {
        return CompletableFuture.supplyAsync(
                () -> {
                    List<Lobby> allLobbies = storage().getLobbies();
                    return allLobbies;
                }
        );
    }

    @Override
    public CompletableFuture<Void> postLobby(Lobby lobby) {
        return CompletableFuture.supplyAsync(
                () -> {
                    try {
                        storage().createLobby(lobby);
                        return null;
                    } catch (MissingException e) {
                        throw new NotFoundResponse();
                    }
                }

        );
    }

    @Override
    public CompletableFuture<Void> addPlayerToLobby(Integer lobbyId, String username) {
        return CompletableFuture.supplyAsync(
                () -> {
                    try {
                        storage().addToLobby(lobbyId, username);
                        return null;
                    }catch (MissingException e){
                        throw new NotFoundResponse();
                    }catch (ConflictException e){
                        throw new ConflictResponse();
                    }
                }
        );
    }

    @Override
    public CompletableFuture<Void> removePlayerFromLobby(Integer lobbyId, String username) {
        return CompletableFuture.supplyAsync(
                () -> {
                    try {
                        storage().removePlayerFromLobby(lobbyId, username);
                        return null;
                    }catch (MissingException e){
                        throw new NotFoundResponse();
                    }
                }
        );
    }

    @Override
    public CompletableFuture<Lobby> getLobbyById(Integer lobbyId) {
        return CompletableFuture.supplyAsync(
                () -> {
                    try {
                        return storage().getLobbyById(lobbyId);
                    }catch (MissingException e){
                        throw new NotFoundResponse();
                    }
                }
        );
    }

}
