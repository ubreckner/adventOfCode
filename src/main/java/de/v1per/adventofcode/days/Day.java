package de.v1per.adventofcode.days;

import java.util.List;

public abstract class Day<T> {

    protected List<T> list;

    void play(List<T> list) {
        this.list = list;
        this.one();
        this.two();
    }

    abstract void one();

    abstract void two();
}
