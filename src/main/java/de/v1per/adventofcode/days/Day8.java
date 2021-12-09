package de.v1per.adventofcode.days;

import de.v1per.adventofcode.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day8 extends Day<String> {

    public static void main(String[] args) {
        List<String> list = Utils.readFile("day8.txt", String.class);
        new Day8().play(list);
    }

    @Override
    void one() {
        Map<Integer, Long> outputLengths = list.stream()
                .flatMap(s -> Arrays.stream(s.split(" \\| ")[1].split(" ")))
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));

        //only number 1 has length 2
        //only number 4 has length 3
        //only number 7 has length 4
        //only number 8 has length 7
        System.out.println(
                "8.1: " + (outputLengths.get(2) + outputLengths.get(3) + outputLengths.get(4) + outputLengths.get(7)));
    }


    @Override
    void two() {
    }
}
