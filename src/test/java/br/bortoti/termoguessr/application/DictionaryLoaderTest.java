package br.bortoti.termoguessr.application;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryLoaderTest {

    @Test
    void load() throws IOException {
        var res = DictionaryLoader.Load("words.txt");

        assertNotNull(res);
    }
}