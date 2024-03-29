package common;


import common.exception.ConflictException;
import common.exception.MissingException;

import java.util.*;

/**
 * This class is the implementation of LOCAL Battleship
 * It means it's an instance of the state of the game.
 * It contains the logic of the game. The LOCAL means it's local on host (in this case LOCAL in the server not in the client)
 * */

public class LocalBattleship implements Battleship {



    private List<Player> players = new ArrayList<>();

    private List<Lobby> lobbies = new ArrayList<>();

    private List<Map<Lobby, Battlefield>> battlefields = new ArrayList<>();//contains all the lobby associated with the lobby

    private int lobbiesCounter = 0;


    @Override
    public void connectPlayer(Player player) throws ConflictException, IllegalArgumentException {
        var copy = new Player(player.getUsername()); //Defensive copy
        if (copy.getUsername() == null || copy.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username vuoto");
        }
        if(players.contains(copy)){
            throw new ConflictException("Username " + copy.getUsername() + " già in uso!");
        }

        players.add(copy);

    }

    @Override
    public List<Lobby> getLobbies() {
        return List.copyOf(this.lobbies);
    }

    @Override
    public Lobby createLobby(Lobby lobby) throws MissingException {
        Lobby newLobby = new Lobby(generateLobbyId(), lobby.getName());
        lobbiesCounter++;
        lobbies.add(newLobby);
        return newLobby;
    }


    private Set<Integer> generatedLobbyId = new HashSet<>();

    private int generateLobbyId() {
        Random random = new Random();
        int lobbyId = 0;
        do {
            lobbyId = random.nextInt(10000);
        } while (generatedLobbyId.contains(lobbyId));
        return lobbyId;
    }

    private Set<Integer> generatedBattlefieldId = new HashSet<>();
    private int generateBattlefieldId() {
        Random random = new Random();
        int battlefieldId = 0;
        do {
            battlefieldId = random.nextInt(10000);
        } while (generatedBattlefieldId.contains(battlefieldId));
        return battlefieldId;
    }

    @Override
    public Player findPlayer(String username) throws MissingException {
        boolean foundUser = false;
        int userIndex = -1;
        for(int i = 0; i < this.players.size(); i++){
            if(players.get(i).getUsername().equals(username)){
                foundUser = true;
                userIndex = i;
                break;
            }
        }
        if(!foundUser){
            throw new MissingException("L'utente con username " + username + " è inesistente.");
        }
        return this.players.get(userIndex);
    }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public void addToLobby(Integer lobbyId, String username) throws MissingException, ConflictException {
        Lobby lobby = this.getLobbyById(lobbyId);
        Player player = this.findPlayer(username);

        if(lobby.isFull() || lobby.contains(player)) {
            throw new ConflictException("Lobby " + lobbyId + " piena oppure la lobby gia contiene il suddetto giocatore");
        }


        lobby.addPlayer(player);
        //se dopo che aggiungo il giocatore, la lobby si riempe allora creo un battlefield per la lobby corrispondente
        if (lobby.isFull()) { //è full se ci sono 4 giocatori
            this.createBattlefieldForLobby(lobby);
        }

    }

    @Override
    public Lobby getLobbyById(int idLobby) throws MissingException {
        boolean foundLobby = false;
        int lobbyIndex = -1;
        for(int i = 0; i < this.lobbies.size(); i++){
            System.err.println(lobbies.get(i).getId());
            if(lobbies.get(i).getId() == idLobby){

                foundLobby = true;
                lobbyIndex = i;
                break;
            }
        }
        if(!foundLobby){
            throw new MissingException("La lobby con codice " + idLobby + " è inesistente.");
        }
        return this.lobbies.get(lobbyIndex);
    }

    @Override
    public void removePlayerFromLobby(Integer lobbyId, String username) throws MissingException {
        Lobby lobby = getLobbyById(lobbyId);
        List<Player> players = lobby.getPlayers();

        Iterator<Player> iterator = players.iterator();
        while (iterator.hasNext()) {
            Player player = iterator.next();
            if (player.getUsername().equals(username)) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public void createBattlefield() {

    }

    @Override
    public void createBattlefieldForLobby(Integer lobbyId) {

    }

    @Override
    public void createBattlefieldForLobby(Lobby lobby) { //CREATE BATTLEFIELD WHEN THE LOBBY IS FULL
        int battlefieldId = generateBattlefieldId();
        Battlefield battlefield = new Battlefield(lobby, battlefieldId);

        Map<Lobby, Battlefield> lobbyBattlefieldMap = new HashMap<>();
        lobbyBattlefieldMap.put(lobby, battlefield);
        this.battlefields.add(Map.copyOf(lobbyBattlefieldMap));

    }


  /*  @Override
    public void createBattlefield() {
        if () {

        }
    }*/

    @Override
    public String addShipsToBattlefield(PlayerShip content) throws MissingException, ConflictException {
        Player player = this.findPlayer(content.getPlayer()); //se esiste il giocatore

        Battlefield battlefieldFound = null;
        for (Map<Lobby, Battlefield> lobbyBattlefieldMap : battlefields) {
            for (Map.Entry<Lobby, Battlefield> entry : lobbyBattlefieldMap.entrySet()) {
                Lobby lobby = entry.getKey();
                if (lobby.getPlayers().contains(player)) {
                    battlefieldFound = entry.getValue();
                    break;
                }
            }
            if (battlefieldFound != null) {
                break;
            }
        }

        if (battlefieldFound == null) {
            throw new MissingException("Battlefield doesn t exists");
        }

        battlefieldFound.addShipToBattlefield(content);

        return "ok";
    }

    @Override
    public List<Map<Lobby, Battlefield>> getBattlefields() {
        return List.copyOf(this.battlefields);
    }

    @Override
    public Battlefield getBattlefieldById(Integer battlefieldId) throws MissingException {
        for (Map<Lobby, Battlefield> lobbyBattlefieldMap : battlefields) {
            for (Map.Entry<Lobby, Battlefield> entry : lobbyBattlefieldMap.entrySet()) {
                Lobby lobby = entry.getKey();
                Battlefield battlefield = entry.getValue();
                if (battlefield.getIdBattlefield() == battlefieldId) {
                    return battlefield;
                }
            }
        }
        throw new MissingException("Il campo di battaglia con ID " + battlefieldId + " non è stato trovato.");
    }


}
