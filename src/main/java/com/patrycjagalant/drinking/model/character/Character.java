package com.patrycjagalant.drinking.model.character;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Character {
    private double stomachCapacity;

    @Override
    public String toString() {
        return "stomachCapacity=" + this.getStomachCapacity();
    }
}
