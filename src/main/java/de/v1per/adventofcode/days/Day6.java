package de.v1per.adventofcode.days;

import de.v1per.adventofcode.beans.Fish;
import de.v1per.adventofcode.utils.Utils;

import java.util.*;
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
        List<Fish> toAdd = Arrays.stream(list.get(0).split(",")).map(l -> new Fish(Long.parseLong(l))).collect(
                Collectors.toList());

        List<Fish> fishList = new ArrayList<>();
        addFishesToList(fishList, toAdd);

        int maxDays = 256;
        for (int i = 0; i < maxDays; i++) {
            toAdd.clear();
            for (Fish fish : fishList) {
                fish.setTtr(fish.getTtr() - 1);
                //it all needs to take one step longer
                if (fish.getTtr() == -1) {
                    Fish addedFish = new Fish(8);
                    addedFish.setCount(fish.getCount());
                    toAdd.add(addedFish);
                }
            }
            rearrangeFishes(fishList);
            addFishesToList(fishList, toAdd);
        }
        long sum = fishList.stream().mapToLong(Fish::getCount).sum();
        System.out.println("6.2: " + sum);
    }

    private void addFishesToList(List<Fish> fishList, List<Fish> toAdd) {
        for (Fish add : toAdd) {
            boolean added = false;
            for (Fish fish : fishList) {
                if (fish.getTtr() == add.getTtr()) {
                    fish.setCount(fish.getCount() + 1);
                    added = true;
                }
            }
            //if the fish hasn't been added it's a new ttr-class
            if (!added) {
                fishList.add(add);
            }
        }
    }

    private void rearrangeFishes(List<Fish> fishList) {
        Iterator<Fish> iter = fishList.iterator();
        long resetCnt = 0;
        boolean removed = false;
        while (iter.hasNext()) {
            Fish fish = iter.next();
            if (fish.getTtr() == -1) {
                resetCnt += fish.getCount();
                iter.remove();
                removed = true;
            }
        }
        boolean found = false;
        for (Fish fish : fishList) {
            if (fish.getTtr() == 6) {
                fish.setCount(fish.getCount() + resetCnt);
                found = true;
            }
        }
        if (removed && !found) {
            Fish addedFish = new Fish(6);
            addedFish.setCount(resetCnt);
            fishList.add(addedFish);
        }
    }
}
