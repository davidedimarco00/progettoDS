package common;

public enum ShipType {
    AIRCRAFT_CARRIER(5),
    BATTLESHIP(4),
    CRUISER(3),
    DESTROYER(3),
    SUBMARINE(2);

    private final int length;

    ShipType(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}