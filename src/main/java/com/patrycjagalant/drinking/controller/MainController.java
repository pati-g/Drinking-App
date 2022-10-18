package com.patrycjagalant.drinking.controller;

import com.patrycjagalant.drinking.dto.CharacterConverter;
import com.patrycjagalant.drinking.dto.CharacterDto;
import com.patrycjagalant.drinking.dto.VesselDto;
import com.patrycjagalant.drinking.model.character.Character;
import com.patrycjagalant.drinking.model.character.CharacterFactory;
import com.patrycjagalant.drinking.model.character.CharacterType;
import com.patrycjagalant.drinking.model.vessel.VesselFactory;
import com.patrycjagalant.drinking.model.vessel.VesselType;
import com.patrycjagalant.drinking.service.DrinkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@RequiredArgsConstructor
@Controller
public class MainController {
    public static final String REDIRECT_HOME = "redirect:/";
    public static final String KIND = "kind";
    public static final String REDIRECT_CHARACTER = "redirect:/character";
    private static final String CHARACTER = "character";
    private static final String ERROR = "error";
    private final CharacterFactory characterFactory;
    private final CharacterConverter characterConverter;
    private final VesselFactory vesselFactory;
    private final DrinkingService drinkingService;

    @GetMapping("/")
    public String viewMainPage(Model model) {
        model.addAttribute("allCharacters", CharacterType.values());
        model.addAttribute(CHARACTER, new CharacterDto());
        return "index";
    }

    @GetMapping("/character")
    public String viewCharacter(Model model) {
        model.addAttribute("vessel", new VesselDto());
        return CHARACTER;
    }

    @PostMapping("/create")
    public String createCharacter(@ModelAttribute("character") CharacterDto characterDto,
                                  RedirectAttributes redirectAttributes) {
        String type = characterDto.getCharacterType();
        double capacity = characterDto.getStomachCapacity();
        if (Arrays.stream(CharacterType.values()).noneMatch(t -> t.name().equalsIgnoreCase(type))) {
            redirectAttributes.addFlashAttribute(ERROR, "Incorrect character type");
            return REDIRECT_HOME;
        } else if (capacity <= 0) {
            redirectAttributes.addFlashAttribute(ERROR, "Stomach capacity must be greater than 0");
            return REDIRECT_HOME;
        }
        Character character = characterFactory.createCharacter(characterDto.getCharacterType(),
                characterDto.getStomachCapacity());
        redirectAttributes.addFlashAttribute(CHARACTER, characterConverter.convertToDto(character));
        redirectAttributes.addFlashAttribute(KIND, type.toLowerCase());
        redirectAttributes.addFlashAttribute("allVessels", VesselType.values());
        return REDIRECT_CHARACTER;
    }

    @PostMapping("/drink")
    public String performDrinking(@ModelAttribute("vessel") VesselDto vesselDto,
                                  @ModelAttribute("character") CharacterDto characterDto,
                                  RedirectAttributes redirectAttributes) {
        String type = vesselDto.getType();
        double capacity = vesselDto.getCapacity();
        if (Arrays.stream(VesselType.values()).noneMatch(t -> t.name().equalsIgnoreCase(type))) {
            redirectAttributes.addFlashAttribute(ERROR, "Incorrect vessel type");
        } else if (capacity <= 0) {
            redirectAttributes.addFlashAttribute(ERROR, "Vessel capacity must be greater than 0");
        }
        Character character = characterConverter.convertToModel(characterDto);
        try {
            drinkingService.drink(character,
                    vesselFactory.createVessel(type, capacity));
            if (character.getStomachCapacity() == 0) {
                redirectAttributes.addFlashAttribute("info",
                        "Your character is full, it's time to create a new one!");
                return REDIRECT_HOME;
            }
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute(ERROR, e.getMessage());
        }
        redirectAttributes.addFlashAttribute(CHARACTER, characterConverter.convertToDto(character));
        redirectAttributes.addFlashAttribute(KIND, characterDto.getCharacterType().toLowerCase());
        redirectAttributes.addFlashAttribute("allVessels", VesselType.values());
        return REDIRECT_CHARACTER;
    }
}
