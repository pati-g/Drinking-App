package com.patrycjagalant.drinking.model.character;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Human extends Character {

    public Human(double stomachCapacity) {
        super(stomachCapacity);
    }

    @Override
    public String toString() {
        return "Human{stomachCapacity=" + this.getStomachCapacity() + "}";
    }
}
