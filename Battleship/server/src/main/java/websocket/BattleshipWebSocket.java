package websocket;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import websocket.connection.ConnectionHandler;
import websocket.connection.ConnectionHandlerImpl;
import websocket.connection.Path;

import java.util.ArrayList;
import java.util.List;

import static j2html.TagCreator.article;
import static j2html.TagCreator.b;
import static j2html.TagCreator.p;
import static j2html.TagCreator.span;

public class BattleshipWebSocket {

    public BattleshipWebSocket(final int port) {
        //this is the configuration of websocket
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);
            config.staticFiles.add("/notification", Location.CLASSPATH);
            config.staticFiles.add("/attack", Location.CLASSPATH);
            config.staticFiles.add("/time", Location.CLASSPATH);
        }).start(port);


        //create a connection handler configurator for the app that handle all websocket endpoint (paths)
        List<Path> pathList = new ArrayList<>();
        pathList.add(new Path("/connection"));
        pathList.add(new Path("/lobby"));
        pathList.add(new Path("/battlefield"));
        pathList.add(new Path("/game"));

        ConnectionHandler connectionHandler = new ConnectionHandlerImpl(app, pathList);
        connectionHandler.configure();
    }








}