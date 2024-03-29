package websocket;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.websocket.WsContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static j2html.TagCreator.article;
import static j2html.TagCreator.attrs;
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

        //create a connection handler configurator for the app that handle all endpoint (paths)
        List<Path> pathList = new ArrayList<>();
        pathList.add(new Path("/notification"));
        pathList.add(new Path("/chat"));


        ConnectionHandler connectionHandler = new ConnectionHandlerImpl(app, pathList);
        connectionHandler.configure();
    }








}