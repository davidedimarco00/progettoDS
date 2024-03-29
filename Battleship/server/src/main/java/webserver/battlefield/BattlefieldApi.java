package webserver.battlefield;

import common.Battleship;
import common.Lobby;
import common.PlayerShip;
import webserver.battlefield.impl.BattlefieldApiImpl;
import common.Battlefield;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface BattlefieldApi {
    static BattlefieldApi of(Battleship storage) {
        return new BattlefieldApiImpl(storage);
    }

    CompletableFuture<PlayerShip> addShipToBattlefield(PlayerShip content);

    CompletableFuture<List<Map<Lobby, Battlefield>>> getBattlefields();

    CompletableFuture<Battlefield> getBattlefieldById(Integer integer);
}
