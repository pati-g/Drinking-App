package com.patrycjagalant.drinking.model.character;

import lombok.Getter;

@Getter
public enum CharacterType {
    HUMAN, CAT;

    @Override
    public String toString() {
        return this.name();
    }
}
