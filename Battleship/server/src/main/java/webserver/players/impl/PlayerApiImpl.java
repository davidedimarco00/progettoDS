package webserver.players.impl;

import common.Battleship;
import common.LocalBattleship;
import common.Player;
import common.exception.ConflictException;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.ConflictResponse;
import webserver.AbstractApi;
import webserver.players.PlayerApi;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PlayerApiImpl extends AbstractApi implements PlayerApi {

    public PlayerApiImpl(Battleship storage) {
        super(storage);
    }


    @Override
    public CompletableFuture<Void> connectPlayer(Player player) {
        return CompletableFuture.supplyAsync(
                () -> {
                    try{
                        storage().connectPlayer(player);
                        return null;
                    }catch (ConflictException e) {
                        throw new ConflictResponse();
                    }catch (IllegalArgumentException e){
                        throw new BadRequestResponse();
                    }
                }
        );
    }

    @Override
    public CompletableFuture<Void> disconnectPlayer(String nicknameUser) {
        return null;
    }

    @Override
    public CompletableFuture<Player> findPlayer(String nicknameUser) {
        return null;
    }

    @Override
    public CompletableFuture<List<Player>> getPlayers() {
        return CompletableFuture.supplyAsync(
                () -> storage().getPlayers()
        );
    }
}
