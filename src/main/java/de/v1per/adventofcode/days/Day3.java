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
                    .collect(Collectors.groupingBy(s -> s.substring(finalI, finalI + 1), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue());
            gammaRateStr.append(mostCommonBit.get().getKey());
        }
        //Least common bits are the 2-complement of the gamma bits
        String epsilonRateStr = Arrays.stream(gammaRateStr.toString().split(""))
                .map(s -> s.equals("0") ? "1" : "0")
                .collect(Collectors.joining());
        gammaRate = Long.parseLong(gammaRateStr.toString(), 2);
        epsilonRate = Long.parseLong(epsilonRateStr, 2);

        System.out.println("3.1: " + gammaRate * epsilonRate);
    }

    @Override
    void two() {
        long o2Rating;
        long co2Rating;
        StringBuilder o2Str = new StringBuilder();
        StringBuilder co2Str = new StringBuilder();
        List<String> o2List = this.list;
        List<String> co2List = this.list;
        //boldly assume every row is the same size as the first
        for (int i = 0; i < this.list.get(0).length(); i++) {
            int finalI = i;
            Optional<Map.Entry<String, Long>> mostCommonBit = o2List.stream()
                    .collect(Collectors.groupingBy(s -> s.substring(finalI, finalI + 1), Collectors.counting()))
                    .entrySet()
                    .stream()
                    //Special comparator to take into account what happens when there are equally many numbers left
                    .max((Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) -> ((e1.getValue() > e2.getValue()) || (e1.getValue()
                            .equals(e2.getValue()) && e1.getKey().equals("1"))) ? 1 : -1);

            o2Str.append(mostCommonBit.get().getKey());
            o2List = o2List.stream().filter(s -> s.startsWith(o2Str.toString())).collect(Collectors.toList());

            Optional<Map.Entry<String, Long>> leastCommonBit = co2List.stream()
                    .collect(Collectors.groupingBy(s -> s.substring(finalI, finalI + 1), Collectors.counting()))
                    .entrySet()
                    .stream()
                    //Special comparator to take into account what happens when there are equally many numbers left
                    .min((Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) -> ((e1.getValue() > e2.getValue()) || (e1.getValue()
                            .equals(e2.getValue()) && e1.getKey().equals("1"))) ? 1 : -1);

            co2Str.append(leastCommonBit.get().getKey());
            co2List = co2List.stream().filter(s -> s.startsWith(co2Str.toString())).collect(Collectors.toList());
        }

        o2Rating = Long.parseLong(o2Str.toString(), 2);
        co2Rating = Long.parseLong(co2Str.toString(), 2);
        System.out.println("3.2:  " + o2Rating * co2Rating);
    }

}
