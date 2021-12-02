package de.v1per.adventofcode.days;

import de.v1per.adventofcode.utils.Utils;

import java.util.List;

public class Day2 extends Day {

    public static void main(String[] args) {
        List<String> list = Utils.readFile("day2.txt", String.class);
        new Day2().play(list);
    }

    @Override
    void one() {
        long horizontalPos = 0;
        long depth = 0;
        for (String nav : (List<String>) this.list) {
            String[] navArray = nav.split(" ");
            switch (navArray[0]) {
                case "forward":
                    horizontalPos += Integer.parseInt(navArray[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(navArray[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(navArray[1]);
                    break;
            }
        }
        System.out.println("2.1: " + horizontalPos * depth);
    }

    @Override
    void two() {
        long horizontalPos = 0;
        long depth = 0;
        long aim = 0;

        for (String nav : (List<String>) this.list) {
            String[] navArray = nav.split(" ");
            switch (navArray[0]) {
                case "forward":
                    long movementUnits = Integer.parseInt(navArray[1]);
                    horizontalPos += movementUnits;
                    depth += aim * movementUnits;
                    break;
                case "down":
                    aim += Integer.parseInt(navArray[1]);
                    break;
                case "up":
                    aim -= Integer.parseInt(navArray[1]);
                    break;
            }
        }

        System.out.println("2.2: " + horizontalPos * depth);
    }
}
