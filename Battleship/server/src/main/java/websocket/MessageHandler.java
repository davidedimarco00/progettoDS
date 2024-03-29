package websocket;

import common.Player;
import io.javalin.websocket.WsContext;

public interface MessageHandler {
    void sendMessageToPlayer(Player player, String message);

    void sendMessageToPlayer(String username, String message);

    void broadcastMessage(String sender, String message); //send a message to all client connected

    void broadcastMessage(Player player, String message); //send a message to all client connected

    void addClient(WsContext ctx, String username);

    void removeClient(WsContext ctx, String username);
}
