package de.v1per.adventofcode.days;

import de.v1per.adventofcode.utils.Utils;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day7 extends Day<String> {

    public static void main(String[] args) {
        List<String> list = Utils.readFile("day7.txt", String.class);
        new Day7().play(list);
    }

    @Override
    void one() {
        List<Integer> posList = Arrays.stream(list.get(0).split(",")).map(Integer::valueOf).collect(
                Collectors.toList());
        int median = posList.stream().sorted().collect(Collectors.collectingAndThen(Collectors.toList(), positions -> {
            int count = positions.size();
            if (count % 2 == 0) {
                return (positions.get(count / 2 - 1) + positions.get(count / 2)) / 2;
            } else {
                return positions.get(count / 2);
            }
        }));

        int fuelUsed = 0;
        for (int pos : posList) {
            fuelUsed += Math.abs(pos - median);
        }

        System.out.println("7.1: " + fuelUsed + " at " + median);
    }

    @Override
    void two() {
        List<Integer> posList = Arrays.stream(list.get(0).split(",")).map(Integer::valueOf).collect(
                Collectors.toList());
        IntSummaryStatistics stats = posList.stream().mapToInt(i -> i).summaryStatistics();
        //Let's halve the range in which we are searching
        int middle = (stats.getMax() - stats.getMin()) / 2;
        Map<Boolean, List<Integer>> map = posList.stream().collect(Collectors.groupingBy(l -> l > middle));

        int min = stats.getMin();
        int max = stats.getMax();
        if (map.get(false).size() > map.get(true).size()) {
            max = middle;
        } else {
            min = middle;
        }

        int minFuelUsed = 0;
        int minpos = 0;
        for (int i = min; i <= max; i++) {
            int fuelUsed = 0;
            for (int pos : posList) {
                int stepsToTarget = Math.abs(pos - i);
                //phew this is always even LETS GO INT!
                fuelUsed += stepsToTarget * (1 + stepsToTarget) / 2;
            }
            if (minFuelUsed == 0 || minFuelUsed > fuelUsed) {
                minFuelUsed = fuelUsed;
                minpos = i;
            }
        }
        System.out.println("7.2: " + minFuelUsed + " at " + minpos);
    }
}
