package common;

import common.exception.ConflictException;
import common.exception.MissingException;

import java.util.List;
import java.util.Map;

public interface Battleship {

    /**
     * Here goes all methods of Batleship.
     * scope: this class contains all methods exposed by Battleship. See lab6 to understand.
     *
     * I write all following the Batthleship flows in our app
     *
     * */


    /**
     * First Step: connect player to the server
     * */

    void connectPlayer(Player player) throws ConflictException, IllegalArgumentException;


    List<Lobby> getLobbies();

    Lobby createLobby(Lobby lobby) throws MissingException;

    Player findPlayer(String clientID) throws MissingException;

    List<Player> getPlayers();

    void addToLobby(Integer lobbyId, String username) throws MissingException, ConflictException;


    Lobby getLobbyById(int idLobby) throws MissingException;

    void removePlayerFromLobby(Integer lobbyId, String username) throws MissingException;

    void createBattlefield();


    void createBattlefieldForLobby(Integer lobbyId);

    void createBattlefieldForLobby(Lobby lobby);

    /*SELECT*/
    String addShipsToBattlefield(PlayerShip content) throws MissingException, ConflictException;

    List<Map<Lobby, Battlefield>> getBattlefields();

    Battlefield getBattlefieldById(Integer battlefieldId) throws MissingException;

    Battlefield getBattlefieldByLobbyId(Integer lobbyId) throws MissingException;
}
