package com.patrycjagalant.drinking.dto;

import com.patrycjagalant.drinking.model.character.Character;
import com.patrycjagalant.drinking.model.character.CharacterFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterConverter {

    private final CharacterFactory characterFactory;
    public CharacterDto convertToDto(Character character) {
        return new CharacterDto(character.getClass().getSimpleName(), character.getStomachCapacity());
    }

    public Character convertToModel(CharacterDto dto) {
        return characterFactory.createCharacter(dto.getCharacterType(), dto.getStomachCapacity());
    }
}
