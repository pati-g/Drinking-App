package com.patrycjagalant.drinking.model.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CharacterFactoryTest {
    private final CharacterFactory characterFactory = new CharacterFactory();

    @Test
    void whenCreateCharacterThenCorrectCharacterChildIsReturned() {
        assertTrue(characterFactory.createCharacter("Human", 5.0) instanceof Human);
        assertTrue(characterFactory.createCharacter("Cat", 2.0) instanceof Cat);
    }
}
