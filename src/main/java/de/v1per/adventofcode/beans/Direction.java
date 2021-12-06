package de.v1per.adventofcode.beans;

import lombok.Data;

import java.util.List;

@Data
public class Direction {
    Point start;
    Point end;

    public Direction(List<Point> list) {
        start = list.get(0);
        end = list.get(1);
    }

    public boolean isDiagonal() {
        return start.getX() != end.getX() && start.getY() != end.getY();
    }

    /**
     * Only when not moving diagonally this method determines the axis alongside which to move.
     *
     * @return
     */
    public String getMovingAxis() {
        String axis;
        if (start.getX() == end.getX()) {
            axis = "y";
        } else {
            axis = "x";
        }
        return axis;
    }

    public int getIncrement(String axis) {
        int res;
        if (axis.equals("y")) {
            res = Integer.compare(end.getY(), start.getY());
        } else {
            res = Integer.compare(end.getX(), start.getX());
        }
        return res;

    }
}
