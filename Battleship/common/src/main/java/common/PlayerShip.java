package common;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This class models each player associated with the ships. It exactly models che username associated
 * with the
 *
 * */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerShip {
    private String player;
    private List<Ship> ships;

    @JsonCreator
    public PlayerShip(@JsonProperty("player") String player, @JsonProperty("ships") List<Ship> ships) {
        this.player = player;
        this.ships = ships;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }
}
