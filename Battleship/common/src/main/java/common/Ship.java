package common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ship {

    private ShipType shipType;
    private List<Cell> cells;

    @JsonCreator
    public Ship(@JsonProperty("shipType") ShipType shipType, @JsonProperty("cells") List<Cell> cells ) {
        this.shipType = shipType;
        //this.cells = new ArrayList<>(shipType.getLength()); //the quantity of occupable cells depends on the type of Ship
        this.cells = cells;
    }

    /*public Ship(@JsonProperty("shipType") ShipType shipType, @JsonProperty("cells") List<Pair<Integer, Integer>> cells) {
        this.shipType = shipType;
        this.cells = cells;
    }*/

    public List<Cell> getCells() {
        return cells;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public int getShipLength() {
        return shipType.getLength();
    }

}
