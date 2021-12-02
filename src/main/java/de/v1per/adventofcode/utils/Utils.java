package de.v1per.adventofcode.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static <T> List<T> readFile(String resource, Class<T> clazz) {
        try {
            Path path = new File(Utils.class.getClassLoader().getResource(resource).getFile()).toPath();
            List<T> res;
            switch (clazz.getSimpleName()) {
                case "String":
                    res = Files.readAllLines(path).stream().map(s -> (T) s).collect(Collectors.toList());
                    break;
                case "Long":
                    res = Files.readAllLines(path).stream().map(s -> (T) Long.valueOf(s))
                               .collect(Collectors.toList());
                    break;
                default:
                    throw new IllegalStateException("I don't know that class " + clazz.getName());
            }
            return res;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
