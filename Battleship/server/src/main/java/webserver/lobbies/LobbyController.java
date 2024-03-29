package webserver.lobbies;

import common.Battleship;
import common.Lobby;
import common.Player;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import io.javalin.openapi.*;
import webserver.BattleshipService;
import webserver.Controller;
import webserver.lobbies.impl.LobbyControllerImpl;

public interface LobbyController extends Controller {

    /**
     * GET
     * This EndPoint return all the lobbies available in the game
     * */
    @OpenApi(
            operationId = "LobbyApi::getLobbies",
            path = BattleshipService.BASE_URL + "/lobbies",
            methods = {HttpMethod.GET},
            tags = {"lobbies"},
            description = "Get all lobbies of the game",
            responses = {
                    @OpenApiResponse(
                            status = "200",
                            description = "List of available lobbies"
                    ),
            }
    )
    void getLobbies(Context context) throws HttpResponseException;

    /**
     * GET
     * Get lobby by id passed in path param
     *
     * */
    @OpenApi(
            operationId = "LobbyApi::getLobbyById",
            path = BattleshipService.BASE_URL + "/lobbies/{lobbyId}",
            methods = {HttpMethod.GET},
            tags = {"lobbies"},
            description = "Get the lobby passed by lobbyId in the path.",
            pathParams = {
                    @OpenApiParam(
                            name = "lobbyId",
                            type = Integer.class,
                            description = "Id of the lobby",
                            required = true
                    )
            },
            responses = {
                    @OpenApiResponse(
                            status = "200",
                            description = "The provided id corresponds to a lobby, the user is now connected to the lobby, nothing is returned"
                    ),
                    @OpenApiResponse(
                            status = "404",
                            description = "Not found: the provided id corresponds to not existing lobby"
                    ),
                    @OpenApiResponse(
                            status = "409",
                            description = "Conflict: the lobby is full"
                    )
            }
    )
    void getLobbyById(Context context) throws HttpResponseException;



















    /**
     * POST
     * This EndPoint adds a new lobby identified by an ID to the game
     */
    @OpenApi(
            operationId = "LobbyApi::postLobby",
            path = BattleshipService.BASE_URL + "/lobbies",
            methods = {HttpMethod.POST},
            tags = {"lobbies"},
            description = "Post a new Lobby",
            requestBody = @OpenApiRequestBody(
                    description = "The creator id client",
                    required = true,
                    content = {
                            @OpenApiContent(
                                    from = Lobby.class,
                                    mimeType = ContentType.JSON
                            )
                    }
            ),
            responses = {
                    @OpenApiResponse(
                            status = "200",
                            description = "The clientID provided is correct, the lobby is created and it's id returned."
                    ),
                    @OpenApiResponse(
                            status = "404",
                            description = "Not found: the provided id corresponds to no known client"
                    )
            }
    )
    void postLobby(Context context) throws HttpResponseException;


    /**
     * PUT
     * Insert a player into a lobby
     *
     *
     * */

    @OpenApi(
            operationId = "LobbyApi::addPlayerToLobby",
            path = BattleshipService.BASE_URL + "/lobbies/{lobbyId}",
            methods = {HttpMethod.PUT},
            tags = {"lobbies"},
            description = "Put the user corresponding to the username passed, in the lobby corresponding to the id passed.",
            pathParams = {
                    @OpenApiParam(
                            name = "lobbyId",
                            type = Integer.class,
                            description = "Id of the lobby",
                            required = true
                    )
            },
            requestBody = @OpenApiRequestBody(
                    description = "The player's username",
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
                            description = "The provided id corresponds to a lobby, the user is now connected to the lobby, nothing is returned"
                    ),
                    @OpenApiResponse(
                            status = "404",
                            description = "Not found: the provided id corresponds to not existing lobby"
                    ),
                    @OpenApiResponse(
                            status = "409",
                            description = "Conflict: the lobby is full"
                    )
            }
    )
    void putPlayerInLobby(Context context) throws HttpResponseException;



    @OpenApi(
            operationId = "LobbyApi::addUserToLobby",
            path = BattleshipService.BASE_URL + "/lobbies/remove/{lobbyId}",
            methods = {HttpMethod.PUT},
            tags = {"lobbies"},
            description = "Disconnect the user corresponding to the nickname passed from the lobby corresponding to the id passed.",
            pathParams = {
                    @OpenApiParam(
                            name = "lobbyId",
                            type = Integer.class,
                            description = "Id of the lobby",
                            required = true
                    )
            },
            requestBody = @OpenApiRequestBody(
                    description = "The player's username",
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
                            description = "The provided id corresponds to a lobby, the user is now disconnected from the lobby, nothing is returned"
                    ),
                    @OpenApiResponse(
                            status = "404",
                            description = "Not found: the provided id corresponds to not existing lobby"
                    )
            }
    )
    void putOutPlayerFromLobby(Context context) throws HttpResponseException;



















    static LobbyController of(String root) {
        return new LobbyControllerImpl(root);
    }
}
