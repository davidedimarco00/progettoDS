package common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Lobby {

    private int id;

    private String name;

    private final List<Player> players = new ArrayList<>();

    private Game game;

    @JsonCreator
    public Lobby(@JsonProperty("id") int id, @JsonProperty("name") String name){
        this.id = id;
        this.name = name;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

   /* public void removeUser(Player player){
        if(this.players.contains(player)){
            this.players.remove(player);
        }
        if(this.game != null){
            this.game.removePlayer(player);
        }
    }*/

    public boolean isFull(){
        return this.players.size() == 4;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

    public int getId(){
        return this.id;
    }

    public int getConnectedPlayerNumber(){ return this.players.size();}

    public void setGame(Game game){
        this.game = game;
    }

    public Game getGame(){return this.game;}

    public boolean contains(Player player) {
            for (Player pl : players) {
                if (player.getUsername().equals(pl.getUsername())) {
                    return true;
                }
            }
            return false;
        }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}