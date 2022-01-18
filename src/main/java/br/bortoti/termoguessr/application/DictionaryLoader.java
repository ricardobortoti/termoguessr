package br.bortoti.termoguessr.application;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class DictionaryLoader {
    public static List<String> Load(String listName) throws IOException {
        Set<String> resultSet = new HashSet<String>();

        File file = ResourceUtils.getFile(String.format("classpath:%s",listName));
        FileReader fr=new FileReader(file);

        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
            stream.forEach(s -> {
                resultSet.add(s.toUpperCase(Locale.ROOT));
            });
        }

        return resultSet.stream().toList();
    }
}
