package webserver.players;

import common.Battleship;
import common.Player;
import webserver.players.impl.PlayerApiImpl;

import java.util.concurrent.CompletableFuture;
import java.util.List;

public interface PlayerApi {
    static PlayerApi of(Battleship storage) {
        return new PlayerApiImpl(storage);
    }

    CompletableFuture<Void> connectPlayer(Player user);

    CompletableFuture<Void> disconnectPlayer(String nicknameUser);

    CompletableFuture<Player> findPlayer(String nicknameUser);

    CompletableFuture<List<Player>> getPlayers();
}
