package webserver.battlefield.impl;

import common.Battlefield;
import common.Battleship;
import common.Lobby;
import common.PlayerShip;
import common.exception.MissingException;
import io.javalin.http.NotFoundResponse;
import webserver.AbstractApi;
import webserver.battlefield.BattlefieldApi;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class BattlefieldApiImpl extends AbstractApi implements BattlefieldApi {

    public BattlefieldApiImpl(Battleship storage) {
        super(storage);
    }

    @Override
    public CompletableFuture<PlayerShip> addShipToBattlefield(PlayerShip content) {
        return CompletableFuture.supplyAsync(
                () -> {
                    try {
                        storage().addShipsToBattlefield(content);
                        return content;
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                }

        );
    }

    @Override
    public CompletableFuture<List<Map<Lobby, Battlefield>>> getBattlefields() {
        return CompletableFuture.supplyAsync(
                () -> {
                    List<Map<Lobby, Battlefield>> allBattlefields = storage().getBattlefields();
                    return allBattlefields;
                }
        );
    }

    @Override
    public CompletableFuture<Battlefield> getBattlefieldById(Integer battlefieldId) {
       return CompletableFuture.supplyAsync(
                () -> {
                    try {
                        return storage().getBattlefieldById(battlefieldId);
                    }catch (MissingException e){
                        throw new NotFoundResponse();
                    }
                });
    }
}
