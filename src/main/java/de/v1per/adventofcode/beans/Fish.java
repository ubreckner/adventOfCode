package de.v1per.adventofcode.beans;

import lombok.Data;

@Data
public class Fish {
    long ttr;
    long count;

    public Fish(long timeToReproduce) {
        ttr = timeToReproduce;
        count = 1;
    }
}
