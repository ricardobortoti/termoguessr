package br.bortoti.termoguessr.application.domain;

import br.bortoti.termoguessr.application.DictionaryLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WordTest {

    private List<String> dictionary;
    private Word word;

    @Test
    void guessMustReturnOnlyWordsWithWithDesiredSize() throws IOException {
        //given
        var expectedList = List.of("UVA", "SIM", "NAO");
        dictionary = DictionaryLoader.Load("guessMustReturnOnlyWordsWithWithDesiredSize.txt");
        word = new Word(3, Collections.EMPTY_MAP, Collections.EMPTY_SET);
        //when
        List<String> result = word.guess(dictionary);
        //then
        assertEquals(3, result.size());
        assertThat(result).hasSameElementsAs(expectedList);
    }

    @Test
    void guessMustReturnWordsWithoutLAndRLetters() throws IOException {
        //given
        dictionary = DictionaryLoader.Load("guessMustReturnWordsWithoutLAndRLetters.txt");
        var expectedList = List.of("AMA", "ANA");
        word = new Word(3, Collections.EMPTY_MAP, Set.of('L','R'));
        //when
        List<String> result = word.guess(dictionary);
        //then
        assertEquals(2, result.size());
        assertThat(result).hasSameElementsAs(expectedList);
    }

    @Test
    void guessMustReturnWordsWithLAndRLetters() throws IOException {
        //given
        dictionary = DictionaryLoader.Load("guessMustReturnWordsWithLAndRLetters.txt");
        var expectedList = List.of("ALEGRA", "LINEAR");
        word = new Word(6,
                       Map.of('L', new Integer[] {-1},
                                    'R', new Integer[] {-1}),
                Collections.EMPTY_SET);
        //when
        List<String> result = word.guess(dictionary);
        //then
        assertEquals(2, result.size());
        assertThat(result).hasSameElementsAs(expectedList);
    }

    @Test
    void guessMustReturnWordsWithLAndRLettersButLMustBeTheFirstLetter() throws IOException {
        //given
        dictionary = DictionaryLoader.Load("guessMustReturnWordsWithLAndRLettersButLMustBeTheFirstLetter.txt");
        var expectedList = List.of( "LINEAR");
        word = new Word(6,
                Map.of('L', new Integer[] {1},
                        'R', new Integer[] {-1}),
                Collections.EMPTY_SET);
        //when
        List<String> result = word.guess(dictionary);
        //then
        assertEquals(1, result.size());
        assertThat(result).hasSameElementsAs(expectedList);
    }

    @Test
    void guesser() throws IOException {
        //given
        dictionary = DictionaryLoader.Load("words.txt");
        var expectedList = List.of( "UTERO");
        word = new Word(5,
                Map.of('I', new Integer[] {2},
                             'A', new Integer[] {5},
                             'H', new Integer[] {4}),
                Set.of('U','T','E','R','O','C','M','D','Z','F','S','G','V','N'));
        /*
        word = new Word(5,
                Map.of('T', new Integer[] {-1},
                        'E', new Integer[] {-1},
                        'R', new Integer[] {-1},
                        'O', new Integer[] {5}),
                Set.of('M'));
         */
        //when
        List<String> result = word.guess(dictionary);
        //then
        assertEquals(1, result.size());
        assertThat(result).hasSameElementsAs(expectedList);
    }
}