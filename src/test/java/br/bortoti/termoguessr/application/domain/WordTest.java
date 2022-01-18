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
        word = new Word(3, Collections.EMPTY_MAP, Collections.EMPTY_MAP);
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
        word = new Word(3,
                Collections.EMPTY_MAP,
                Map.of('L', new Integer[] {-1},
                        'R', new Integer[] {-1}));
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
                Collections.EMPTY_MAP);
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
                Collections.EMPTY_MAP);
        //when
        List<String> result = word.guess(dictionary);
        //then
        assertEquals(1, result.size());
        assertThat(result).hasSameElementsAs(expectedList);
    }

    @Test
    void guessTwoALettersButNotInStartAndEndOfWord() throws IOException {
        //given
        dictionary = DictionaryLoader.Load("guessTwoALettersButNotInStartAndEndOfWord.txt");
        var expectedList = List.of( "SARAR");
        word = new Word(5,
                /*Map.of(
                        'A', new Integer[] {-1}
                        )*/Collections.EMPTY_MAP,
                Map.of(
                        'A', new Integer[] {1,5})
        );
        //when
        List<String> result = word.guess(dictionary);
        //then
        assertEquals(1, result.size());
        assertThat(result).hasSameElementsAs(expectedList);
    }

    @Test
    void guess() throws IOException {
        //given
        dictionary = DictionaryLoader.Load("words.txt");
        var expectedList = List.of( "SARAR");
        word = new Word(5,
                Map.of(
                        /*
                        'F', new Integer[] {1},
                        'R', new Integer[] {2},
                        'T', new Integer[] {4},
                        'A', new Integer[] {5}
                         */
                        ),
                Map.of(
                        /*
                        'O', new Integer[] {-1},
                        'B', new Integer[] {-1},
                        'I', new Integer[] {-1},
                        'N', new Integer[] {-1},
                        'E', new Integer[] {-1},
                        'S', new Integer[] {-1},
                        'R', new Integer[] {3,4},
                        'A', new Integer[] {2,4}
                         */
                        )

        );
        //when
        List<String> result = word.guess(dictionary);
        //then
        assertEquals(1, result.size());
        assertThat(result).hasSameElementsAs(expectedList);
    }
}