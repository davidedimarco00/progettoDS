package webserver.game;

import webserver.Controller;
import webserver.game.impl.GameControllerImpl;

public interface GameController extends Controller {

    static GameController of(String root) {
        return new GameControllerImpl(root);
    }
}
