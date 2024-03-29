package common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import common.exception.ConflictException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Battlefield {

    private Lobby lobby; //every battlefield has a lobby associated with
    private int idBattlefield;

    private final int ROWS = 15;
    private final int COLS = 15;

    private Map<String, Map<ShipType, List<Cell>>> playerShipMap = new HashMap<>();

    @JsonCreator
    public Battlefield(@JsonProperty("lobby") Lobby lobby,  @JsonProperty("id") int idBattlefield) {
        this.lobby = lobby;
        this.idBattlefield = idBattlefield;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public int getIdBattlefield() {
        return idBattlefield;
    }

    public void setIdBattlefield(int idBattlefield) {
        this.idBattlefield = idBattlefield;
    }

    public Map<String, Map<ShipType, List<Cell>>> getPlayerShipMap() {
        return this.playerShipMap;
    }

    public void setPlayerShipMap(Map<String, Map<ShipType, List<Cell>>> playerShipMap) {
        this.playerShipMap = playerShipMap;
    }

    public void addShipToBattlefield(PlayerShip playerShip) throws ConflictException {
        String player = playerShip.getPlayer(); // Ottiene il giocatore associato alla nave.
        List<Ship> listOfShips = playerShip.getShips();

        //verify if the coordinates are valid ship elese throw exception
        for (Ship ship : listOfShips) {
            List<Cell> cells = ship.getCells();
            for (Cell cell : cells) {
                if (cell.getPoint_x() < 0 || cell.getPoint_x() >= this.COLS) {
                    throw new ConflictException();
                }

                if (cell.getPoint_y() < 0 || cell.getPoint_y() >= this.ROWS) {
                    throw new ConflictException();
                }



            }
        }



        Map<ShipType, List<Cell>> shipMap = playerShipMap.getOrDefault(player, new HashMap<>());

        for (Ship ship : listOfShips) {
            List<Cell> cells = shipMap.getOrDefault(ship.getShipType(), new ArrayList<>());
            cells.addAll(ship.getCells());
            shipMap.put(ship.getShipType(), cells);
        }

        playerShipMap.put(player, shipMap);
    }

}
