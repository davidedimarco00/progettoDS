package webserver.game;

import common.Battleship;
import webserver.game.impl.GameApiImpl;

public interface GameApi {

    static GameApi of(Battleship storage) {
        return new GameApiImpl(storage);
    }
}
