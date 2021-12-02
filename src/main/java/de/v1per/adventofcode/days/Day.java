package de.v1per.adventofcode.days;

import java.util.List;

public abstract class Day {

    protected List<?> list;

    void play(List<?> list) {
        this.list = list;
        this.one();
        this.two();
    }

    ;

    abstract void one();

    abstract void two();
}
