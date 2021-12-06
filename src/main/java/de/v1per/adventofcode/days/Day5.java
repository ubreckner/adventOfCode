package de.v1per.adventofcode.days;

import de.v1per.adventofcode.beans.Direction;
import de.v1per.adventofcode.beans.Point;
import de.v1per.adventofcode.utils.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day5 extends Day<String> {

    public static void main(String[] args) {
        List<String> list = Utils.readFile("day5.txt", String.class);
        new Day5().play(list);
    }

    @Override
    void one() {
        List<Direction> directions = list.stream().map(s -> Arrays.stream(s.split("->"))
                .map(sub -> new Point(sub.strip()))
                .collect(Collectors.toList())).map(Direction::new).filter(Predicate.not(Direction::isDiagonal)).collect(
                Collectors.toList());
        Map<Point, Integer> coordinates = generateHotPointMap(directions);

        long dangerousCoordinatesCnt = coordinates.entrySet().stream().filter(e -> e.getValue() >= 2).count();

        System.out.println("5.1: " + dangerousCoordinatesCnt);
    }

    @Override
    void two() {
        List<Direction> directions = list.stream().map(s -> Arrays.stream(s.split("->"))
                .map(sub -> new Point(sub.strip()))
                .collect(Collectors.toList())).map(Direction::new).collect(Collectors.toList());
        Map<Point, Integer> coordinates = generateHotPointMap(directions);

        long dangerousCoordinatesCnt = coordinates.entrySet().stream().filter(e -> e.getValue() >= 2).count();

        System.out.println("5.1: " + dangerousCoordinatesCnt);
    }

    private Map<Point, Integer> generateHotPointMap(List<Direction> directions) {
        Map<Point, Integer> coordinates = new HashMap<>();
        for (Direction direction : directions) {
            Point currentCoordinate = new Point(direction.getStart().getX(), direction.getStart().getY());
            coordinates.merge(currentCoordinate, 1, Integer::sum);
            while (!direction.getStart().equals(direction.getEnd())) {
                int incrementX = direction.getIncrement("x");
                int incrementY = direction.getIncrement("y");
                direction.getStart().setX(direction.getStart().getX() + incrementX);
                direction.getStart().setY(direction.getStart().getY() + incrementY);
                currentCoordinate = new Point(direction.getStart().getX(), direction.getStart().getY());
                coordinates.merge(currentCoordinate, 1, Integer::sum);
            }
        }
        return coordinates;
    }
}
