package com.patrycjagalant.drinking.service;

import com.patrycjagalant.drinking.model.character.Character;
import com.patrycjagalant.drinking.model.vessel.Vessel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DrinkingService {

    public void drink(Character character, Vessel vessel) {
        double vesselVolume = vessel.getVolume();
        double capacityAfterDrinking = character.getStomachCapacity() - vesselVolume;
        if (capacityAfterDrinking >= 0) {
            character.setStomachCapacity(capacityAfterDrinking);
        } else {
            throw new IllegalArgumentException("The character can't fit that amount of liquid!");
        }
    }
}
