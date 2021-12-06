package de.v1per.adventofcode.days;

import de.v1per.adventofcode.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Day6 extends Day<String> {

    public static void main(String[] args) {
        List<String> list = Utils.readFile("nikolausi.txt", String.class);
        new Day6().play(list);
    }

    @Override
    void one() {
        List<Long> fishList = Arrays.stream(list.get(0).split(",")).map(Long::valueOf).collect(Collectors.toList());

        int maxDays = 80;
        for (int i = 0; i < maxDays; i++) {
            ListIterator<Long> iter = fishList.listIterator();
            while (iter.hasNext()) {
                Long fish = iter.next();
                if (fish == 0) {
                    iter.set(6L);
                    iter.add(8L);
                } else {
                    iter.set(fish - 1);
                }
            }
        }

        System.out.println("6.1: " + fishList.size());
    }

    @Override
    void two() {
    }
}
