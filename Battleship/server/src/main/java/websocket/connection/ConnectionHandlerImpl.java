package websocket.connection;

import io.javalin.Javalin;
import websocket.messaging.MessageHandler;
import websocket.messaging.MessageHandlerImpl;

import java.util.List;

public class ConnectionHandlerImpl implements ConnectionHandler {


    private final Javalin app;

    private List<Path> paths;

    private MessageHandler messageHandler;

    public ConnectionHandlerImpl(Javalin app, List<Path> paths) {
        this.app = app;
        this.paths = paths;
        this.messageHandler = new MessageHandlerImpl();
    }

    @Override
    public void configure() {
        for (Path path : this.paths) {
            String pathName = path.getPathName();
            this.app.ws(pathName, ws -> {
                ws.onConnect(ctx ->  {
                    switch (pathName) {
                        case "/attack":
                            break;
                        case "/notification":
                            break;
                        case "/chat":
                            this.messageHandler.addClient(ctx, "username");
                            break;
                    }
                });
                ws.onClose(ctx -> {
                    switch (pathName) {
                        case "/attack":
                            break;
                        case "/notification":
                            break;
                        case "/chat":
                            this.messageHandler.removeClient(ctx, "username");
                            break;
                    }
                });
                ws.onMessage(ctx -> {
                    //broadcastMessage(userUsernameMap.get(ctx), ctx.message());
                });
            });
        }
    }

    public Javalin getApp() {
        return app;
    }

}
