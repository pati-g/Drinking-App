package com.patrycjagalant.drinking.model.character;

import org.springframework.stereotype.Component;

@Component
public class CharacterFactory {
    public Character createCharacter(String type, double capacity) {
        if (type.equalsIgnoreCase("human")) {
            return new Human(capacity);
        } else if (type.equalsIgnoreCase("cat")) {
            return new Cat(capacity);
        }
        return null;
    }
}
