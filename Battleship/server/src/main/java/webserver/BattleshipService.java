package webserver;

import common.Battlefield;
import common.Battleship;
import common.LocalBattleship;
import common.Player;
import common.exception.ConflictException;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.Header;
import io.javalin.plugin.bundled.CorsPlugin;

import websocket.BattleshipWebSocket;
import presentation.GsonUtils;
import webserver.battlefield.BattlefieldController;
import webserver.game.GameController;
import webserver.healthcheck.HealthCheckController;
import webserver.lobbies.LobbyController;
import webserver.players.PlayerController;
import webserver.game.GameController;
import webserver.lobbies.LobbyController;
import webserver.utils.Filters;
import webserver.utils.Plugins;
import webserver.players.PlayerController;
import webserver.utils.Plugins;

import java.net.http.WebSocket;
import java.util.function.Consumer;


public class BattleshipService {

    private static final String API_VERSION = "1.0";

    public static final String BASE_URL = "/battleship/v" + API_VERSION;

    private static final int DEFAULT_PORT_REST = 10000;
    private static final int DEFAULT_PORT_WEBSOCKET = 7070;


    private final int port;
    private final Javalin server;

    private final Battleship localBattleship = new LocalBattleship();

    public BattleshipService(int port) {
        this.port = port;
        server = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();

           /*config.registerPlugin(Plugins.openApiPlugin(API_VERSION, "/doc"));
            config.registerPlugin(Plugins.swaggerPlugin("/doc", "/ui"));
            config.registerPlugin(Plugins.routeOverviewPlugin("/routes"));*/

        });





        // Enable CORS for all routes
        server.before(ctx -> {
            ctx.header("Access-Control-Allow-Origin", "*");
            ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            ctx.header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");
        });

        server.before(path("/*"), Filters.putSingletonInContext(Battleship.class, localBattleship)); //just localBattleship?

        PlayerController.of(path("/players")).registerRoutes(server);
        LobbyController.of(path("/lobbies")).registerRoutes(server);
        BattlefieldController.of(path("/battlefield")).registerRoutes(server);
        BattlefieldController.of(path("/battlefield/ships")).registerRoutes(server);


        HealthCheckController.of(path("/healthcheck")).registerRoutes(server);

    }

    public void start() {
        server.start(port);
    }

    public void stop() {
        server.stop();
    }

    private static String path(String subPath) {
        return BASE_URL + subPath;
    }

    public static void main(String[] args) {
        new BattleshipService(args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT_REST).start(); //questo fa partire il servizio REST

        new BattleshipWebSocket(args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT_WEBSOCKET); //questo fa partire il servizio WEBSOCKET

    }


}
