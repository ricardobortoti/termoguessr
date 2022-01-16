package br.bortoti.termoguessr.application.domain;

import org.w3c.dom.DOMStringList;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Word {
    private Map<Character, Integer[]> have;
    private Set<Character> haveNot;
    private Integer size;

    public Word(Integer size, Map<Character, Integer[]> have, Set<Character> haveNot) {
        this.have = have;
        this.haveNot = haveNot;
        this.size = size;
    }

    private Predicate<String> sizeOf(int size) {
        return s -> s.length() == size;
    }

    private Predicate<String> havingNot(Set<Character> haveNot) {
        return s -> !containSet(s, haveNot);
    }

    private boolean containSet(String word, Set<Character> haveNot) {
        return haveNot.stream().anyMatch(c -> word.indexOf(c) != -1);
    }

    private Predicate<String> having(Map<Character, Integer[]> have) {
        return s -> have.keySet()
                .stream()
                .allMatch(k -> Arrays.stream(have.get(k))
                                .allMatch(index -> containPosition(s, k, index))
                );
    }

    private boolean containPosition(String word, Character character, Integer posititon) {
        return ((posititon != -1) && (word.indexOf(character) == posititon-1)) ||
               ((posititon == -1) && (word.indexOf(character) != -1));
    }

    public List<String> guess(List<String> wordDict) {
        var resultingList = wordDict
                .stream()
                .filter(sizeOf(this.size))
                .filter(havingNot(haveNot))
                .filter(having(have))
                .collect(Collectors.toList());

        return resultingList;
    }
}
