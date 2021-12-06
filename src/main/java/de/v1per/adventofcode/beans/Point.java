package de.v1per.adventofcode.beans;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Point {
    int x;
    int y;

    public Point(String str) {
        List<Integer> list = Arrays.stream(str.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        x = list.get(0);
        y = list.get(1);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
