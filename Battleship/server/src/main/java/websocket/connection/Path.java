package websocket.connection;


/**
 * This class models the path of websocket. localhost:port/{pathName}
 * */
public class Path {
    private final String pathName;

    public Path(String pathName) {
        this.pathName = pathName;
    }

    String getPathName() {
        return this.pathName;
    }

}
