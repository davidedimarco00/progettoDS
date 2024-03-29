package webserver;


import common.Battleship;

public abstract class AbstractApi {
    private final Battleship storage;

    public AbstractApi(Battleship storage) {
        this.storage = storage;
    }

    public Battleship storage() {
        return storage;
    }
}
