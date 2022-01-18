package br.bortoti.termoguessr.application.domain;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Word {
    private Map<Character, Integer[]> have;
    private Map<Character, Integer[]> haveNot;
    private Integer size;

    public Word(Integer size, Map<Character, Integer[]> have, Map<Character, Integer[]> haveNot) {
        this.have = have;
        this.haveNot = haveNot;
        this.size = size;
    }

    private Predicate<String> sizeOf(int size) {
        return s -> s.length() == size;
    }

    private Predicate<String> having(Map<Character, Integer[]> have) {
        return s -> have.keySet()
                .stream()
                .allMatch(k -> Arrays.stream(have.get(k))
                                .allMatch(index -> containPosition(s, k, index))
                );
    }

    private Predicate<String> notHaving(Map<Character, Integer[]> have) {
        return s -> have.keySet()
                .stream()
                .allMatch(k -> Arrays.stream(have.get(k))
                        .noneMatch(index -> containPosition(s, k, index))
                );
    }

    private Set<Integer> checkIndexes(String word, Character character) {
        int index = 0;
        Set<Integer> res = new HashSet<>();
        while (index != -1) {
            index = word.indexOf(character, index);

            if (index != -1)
            {
                res.add(index);
                index++;
            }
        }

        return res;
    }

    private boolean containPosition(String word, Character character, Integer position) {
        Set<Integer> test = checkIndexes(word, character);

        return ((position != -1) && ( checkIndexes(word, character).contains(position-1))) ||
                ((position == -1) && (word.indexOf(character) != -1));
/*
        return ((position != -1) && (word.indexOf(character) == position - 1)) ||
                ((position == -1) && (word.indexOf(character) != -1));

 */
    }

    public List<String> guess(List<String> wordDict) {
        var resultingList = wordDict
                .stream()
                .filter(sizeOf(this.size))
                .filter(having(have))
                .filter(notHaving(haveNot))
                .collect(Collectors.toList());

        return resultingList;
    }
}
