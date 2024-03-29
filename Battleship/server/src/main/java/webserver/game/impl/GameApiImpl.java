package webserver.game.impl;

import common.Battleship;
import webserver.AbstractApi;
import webserver.game.GameApi;

public class GameApiImpl extends AbstractApi implements GameApi {

    public GameApiImpl(Battleship storage) {
        super(storage);
    }
}
