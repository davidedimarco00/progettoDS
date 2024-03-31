package websocket.messaging;


import common.Player;
import io.javalin.websocket.WsContext;
import websocket.logger.Logger;
import websocket.messaging.MessageHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static j2html.TagCreator.*;

public class MessageHandlerImpl implements MessageHandler {

    private static final Map<WsContext, String> connectedClientMap = new ConcurrentHashMap<>(); //the list of all connected client
    private Logger logger = new Logger();

    @Override
    public void sendMessageToPlayer(Player player, String message) {

    }

    @Override
    public void sendMessageToPlayer(String username, String message) {

    }

    @Override
    public void broadcastMessage(String sender, String message) {
        connectedClientMap.keySet().stream().filter(ctx -> ctx.session.isOpen()).forEach(session -> {
            session.send(
                    Map.of(
                            "userMessage", createHtmlMessageFromSender(sender, message),
                            "userlist", connectedClientMap.values()
                    )
            );
        });
    }

    @Override
    public void broadcastMessage(Player player, String message) {

    }

    @Override
    public void addClient(WsContext ctx, String username) {
        connectedClientMap.put(ctx, username);
        Logger.info("Client " + username + " connected!");


        broadcastMessage(username, "ciao");

    }

    @Override
    public void removeClient(WsContext ctx, String username) {
        connectedClientMap.remove(ctx);
        Logger.info("Client " + username + " disconnected!");


        broadcastMessage(username, "ciao");

    }




    private static String createHtmlMessageFromSender(String sender, String message) {
        return article(
                b(sender + " says:"),
                span(attrs(".timestamp"), new SimpleDateFormat("HH:mm:ss").format(new Date())),
                p(message)
        ).render();
    }
}
