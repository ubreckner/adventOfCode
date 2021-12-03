package de.v1per.adventofcode.days;

import de.v1per.adventofcode.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day3 extends Day<String> {

    public static void main(String[] args) {
        List<String> list = Utils.readFile("day3.txt", String.class);
        new Day3().play(list);
    }

    @Override
    void one() {
        long gammaRate;
        long epsilonRate;
        StringBuilder gammaRateStr = new StringBuilder();
        String firstRow = this.list.get(0);
        //boldly assume every row is the same size as the first
        for (int i = 0; i < firstRow.length(); i++) {
            int finalI = i;
            Optional<Map.Entry<String, Long>> mostCommonBit = this.list.stream()
                                                                       .collect(Collectors.groupingBy(
                                                                               s -> s.substring(finalI, finalI + 1),
                                                                               Collectors.counting()))
                                                                       .entrySet()
                                                                       .stream()
                                                                       .max(Map.Entry.comparingByValue());
            gammaRateStr.append(mostCommonBit.get().getKey());
        }
        String epsilonRateStr = Arrays.stream(gammaRateStr.toString().split(""))
                                      .map(s -> s.equals("0") ? "1" : "0")
                                      .collect(Collectors.joining());
        gammaRate = Long.parseLong(gammaRateStr.toString(), 2);
        epsilonRate = Long.parseLong(epsilonRateStr.toString(), 2);

        System.out.println("3.1: " + gammaRate * epsilonRate);
    }

    @Override
    void two() {

    }
}
