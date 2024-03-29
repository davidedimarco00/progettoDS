package webserver.battlefield;

import common.Lobby;
import common.PlayerShip;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import io.javalin.openapi.*;
import webserver.BattleshipService;
import webserver.Controller;
import webserver.battlefield.impl.BattlefieldControllerImpl;

public interface BattlefieldController extends Controller {

    static BattlefieldController of(String root) {
        return new BattlefieldControllerImpl(root);
    }


    /**
     * GET
     * This EndPoint return all the lobbies available in the game
     * */
    @OpenApi(
            operationId = "LobbyApi::getBattlefields",
            path = BattleshipService.BASE_URL + "/battlefield",
            methods = {HttpMethod.GET},
            tags = {"battlefields"},
            description = "Get all battlefields of the game",
            responses = {
                    @OpenApiResponse(
                            status = "200",
                            description = "List of available battlefields"
                    ),
            }
    )
    void getBattlefields(Context context) throws HttpResponseException;

    /**
     * GET
     * Get battlefield by id passed in path param
     * */
    @OpenApi(
            operationId = "LobbyApi::getBattlefieldById",
            path = BattleshipService.BASE_URL + "/battlefield/{battlefieldId}",
            methods = {HttpMethod.GET},
            tags = {"battlefield"},
            description = "Get the battlefield passed by lobbyId in the path.",
            pathParams = {
                    @OpenApiParam(
                            name = "battlefieldId",
                            type = Integer.class,
                            description = "Id of the battlefield",
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
                            description = "Not found: the provided id corresponds to not existing battlefield"
                    ),
                    @OpenApiResponse(
                            status = "409",
                            description = "Conflict: the lobby is full"
                    )
            }
    )
    void getBattlefieldById(Context context) throws HttpResponseException;


































    /**
     * POST
     * This EndPoint adds a new lobby identified by an ID to the game
     */
    @OpenApi(
            operationId = "BattlefieldApi::addShipToBattlefield",
            path = BattleshipService.BASE_URL + "/battlefield/ships",
            methods = {HttpMethod.POST},
            tags = {"ships"},
            description = "Post a new list of ships for the user specified",
            requestBody = @OpenApiRequestBody(
                    description = "Username and list of ships",
                    required = true,
                    content = {
                            @OpenApiContent(
                                    from = String.class,
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
    void postPlayerShips(Context context) throws HttpResponseException;



}
