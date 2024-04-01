package websocket.connection;

import io.javalin.Javalin;
import io.javalin.websocket.WsConfig;
import io.javalin.websocket.WsContext;
import websocket.logger.Logger;
import websocket.messaging.MessageHandler;
import websocket.messaging.MessageHandlerImpl;

import java.util.*;

/**
 *
 * This class handle the connection of client to a certain topic in a certain endpoint.
 * */

public class ConnectionHandlerImpl implements ConnectionHandler {


    private final Javalin app;

    private List<Path> paths;

    private static Map<String, Map<String, Set<WsContext>>> topicSubscribers = new HashMap<>();

    private static MessageHandler messageHandler;


    public ConnectionHandlerImpl(Javalin app, List<Path> paths) {
        this.app = app;
        this.paths = paths;
        this.messageHandler = new MessageHandlerImpl();
    }

    @Override
    public void configure() {
        for (Path path : this.paths) {
            String endpoint = path.getPathName();
            this.app.ws(endpoint, ws -> {
                configureWebSocket(ws, endpoint);
            });
        }
    }
    private static void configureWebSocket(WsConfig ws, String endpoint) {
        ws.onConnect(ctx -> {
            Logger.info("Nuova connessione WebSocket a " + endpoint + " da " + ctx.sessionId());
            ctx.enableAutomaticPings(); //it is helpful to prevent the automatic disconnection of client
        });
        ws.onClose((ctx) -> {
            Logger.warning("Connessione WebSocket chiusa a " + endpoint + " da " + ctx.sessionId() +  " causa: " + ctx.reason());
            //removeSessionFromTopics(session, endpoint);
        });

        ws.onMessage((ctx) -> {
            Logger.info("Messaggio ricevuto da " + ctx.sessionId() + " su " + endpoint + ": " + ctx.message());
            handleMessage(ctx, ctx.message(), endpoint);
        });

    }


    private static void handleMessage(WsContext session, String message, String endpoint) {
        String[] parts = message.split("\\^"); //ho messso il triangolino per non avere problemi col ij JSON, dato che
                                                        //il content sarà sempre di tipo JSON E.g: {"option": ""}

            String action = parts[0];
            String topic = parts[1];

            switch (action) {
                case "subscribe":
                    subscribeToTopic(session, topic, endpoint);
                    break;
                case "publish":
                    String content = parts[2];
                    publishToTopic(topic, content, endpoint);
                    break;
                default:
                    System.out.println("Azione non valida: " + action);
                    break;
            }
    }

    private static void subscribeToTopic(WsContext session, String topic, String endpoint) {
        Map<String, Set<WsContext>> endpointTopics = topicSubscribers.computeIfAbsent(endpoint, k -> new HashMap<>());
        endpointTopics.computeIfAbsent(topic, k -> new HashSet<>()).add(session);
        Logger.info("Il client "+ session.sessionId() + " si è sottoscritto al topic: '" + topic + "' su endpoint " + endpoint);
    }

    private static void publishToTopic(String topic, String message, String endpoint) {
        if (message != null) {
            Map<String, Set<WsContext>> endpointTopics = topicSubscribers.getOrDefault(endpoint, new HashMap<>());
            Set<WsContext> subscribers = endpointTopics.getOrDefault(topic, new HashSet<>());
            Logger.info("Messaggio da pubblicare: " + message);

            for (WsContext subscriber : subscribers) {
                messageHandler.addClient(subscriber, message);
            }
        } else {
            Logger.warning("Messaggio nullo sul topic '" + topic + "' di endpoint " + endpoint);
        }
    }

}
