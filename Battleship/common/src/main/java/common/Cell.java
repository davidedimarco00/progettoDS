package common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cell {
    public int getPoint_x() {
        return point_x;
    }

    public void setPoint_x(int point_x) {
        this.point_x = point_x;
    }

    public int getPoint_y() {
        return point_y;
    }

    public void setPoint_y(int point_y) {
        this.point_y = point_y;
    }

    private int point_x;
    private int point_y;


    @JsonCreator
    public Cell(@JsonProperty("point_x") int point_x, @JsonProperty("point_y") int point_y) {
        this.point_x  = point_x;
        this.point_y = point_y;
    }
}