package com.patrycjagalant.drinking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterDto {
    private String characterType;
    private Double stomachCapacity;
}
