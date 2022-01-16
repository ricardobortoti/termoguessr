package br.bortoti.termoguessr.application;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class DictionaryLoader {
    public static List<String> Load(String listName) throws IOException {
        List<String> result= new ArrayList<>();

        File file = ResourceUtils.getFile(String.format("classpath:%s",listName));
        FileReader fr=new FileReader(file);

        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
            stream.forEach(s -> {
                result.add(s.toUpperCase(Locale.ROOT));
            });
        }

        return result;
    }
}
