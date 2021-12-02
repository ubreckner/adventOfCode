package de.v1per.adventofcode.days;

import de.v1per.adventofcode.utils.Utils;

import java.util.List;

public class Day1 extends Day {
    public static void main(String[] args) {
        List<Long> intList = Utils.readFile("day1.txt", Long.class);
        new Day1().play(intList);
    }

    @Override
    public void one() {
        long prevNumber = 0;
        //-1 because the first entry is a "false" increase
        int res = -1;
        for (long number : (List<Long>) list) {
            if (prevNumber < number) {
                res++;
            }
            prevNumber = number;
        }
        System.out.println("1.1: " + res);
    }

    @Override
    public void two() {
        long prevSum = 0;
        //-1 because the first entry is a "false" increase
        int res = -1;
        int i = 0;
        while (i + 2 < list.size()) {
            long sum = (Long) list.get(i) + (Long) list.get(i + 1) + (Long) list.get(i + 2);
            if (prevSum < sum) {
                res++;
            }
            prevSum = sum;
            i++;
        }
        System.out.println("1.2: " + res);
    }
}
