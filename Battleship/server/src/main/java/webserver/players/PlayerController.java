package webserver.players;

import common.Player;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import io.javalin.openapi.*;
import webserver.Controller;
import webserver.players.impl.PlayerControllerImpl;
import webserver.BattleshipService;


public interface PlayerController extends Controller {

    /**
     * POST
     *
     * This EndPoint handle the connection of a new Player
     * */
    @OpenApi(
            operationId = "PlayerApi::connectPlayer",
            path = BattleshipService.BASE_URL + "/players",
            methods = {HttpMethod.POST},
            tags = {"players"},
            description = "Connect a new player to the game",
            requestBody = @OpenApiRequestBody(
                    description = "The player's data",
                    required = true,
                    content = {
                            @OpenApiContent(
                                    from = Player.class,
                                    mimeType = ContentType.JSON
                            )
                    }
            ),
            responses = {
                    @OpenApiResponse(
                            status = "200",
                            description = "No error. Player is connected"
                    ),
                    @OpenApiResponse(
                            status = "400",
                            description = "Bad request: the nickname can't be empty"
                    ),
                    @OpenApiResponse(
                            status = "409",
                            description = "Conflict: the nickname has already been taken"
                    )
            }
    )
    void postPlayer(Context context) throws HttpResponseException;


    /**
     * GET
     * This EndPoint handle the connection of a new Player
     * */
    @OpenApi(
            operationId = "PlayerApi::getPlayers",
            path = BattleshipService.BASE_URL + "/players",
            methods = {HttpMethod.GET},
            tags = {"players"},
            description = "Get the list of all players",
            responses = {
                    @OpenApiResponse(
                            status = "200",
                            description = "No error. The list of connected user is returned",
                            content = {
                            @OpenApiContent(
                                    from = Player[].class,
                                    mimeType = ContentType.JSON
                            )
                    }
                    )
            }
    )

    void getPlayers(Context context) throws HttpResponseException;
























    static PlayerController of(String root) {
        return new PlayerControllerImpl(root);
    }
}
