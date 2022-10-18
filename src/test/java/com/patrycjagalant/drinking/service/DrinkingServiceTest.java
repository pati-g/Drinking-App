package com.patrycjagalant.drinking.service;

import com.patrycjagalant.drinking.model.character.Cat;
import com.patrycjagalant.drinking.model.character.Character;
import com.patrycjagalant.drinking.model.vessel.Bowl;
import com.patrycjagalant.drinking.model.vessel.Vessel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DrinkingServiceTest {
    private final DrinkingService drinkingService = new DrinkingService();

    @Test
    void whenDrinkMethodInvokedCorrectlyThenCharactersCapacityDecreases() {
        Character character = new Cat(6);
        Vessel vessel = new Bowl(2);
        drinkingService.drink(character, vessel);
        assertEquals(4, character.getStomachCapacity());
    }

    @Test
    void whenVesselCapacityTooHighThenExceptionThrown() {
        Character character = new Cat(6);
        Vessel vessel = new Bowl(12);
        assertThrows(IllegalArgumentException.class, () -> drinkingService.drink(character, vessel));
    }

    @Test
    void whenStomachCapacityBecomesZeroThenSuccess() {
        Character character = new Cat(6);
        Vessel vessel = new Bowl(6);
        drinkingService.drink(character, vessel);
        assertEquals(0, character.getStomachCapacity());
    }
}
