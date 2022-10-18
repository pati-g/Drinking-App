package com.patrycjagalant.drinking.controller;

import com.patrycjagalant.drinking.dto.CharacterDto;
import com.patrycjagalant.drinking.dto.VesselDto;
import com.patrycjagalant.drinking.model.character.CharacterFactory;
import com.patrycjagalant.drinking.model.character.CharacterType;
import com.patrycjagalant.drinking.model.vessel.VesselFactory;
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
    private final CharacterFactory characterFactory;
    private final VesselFactory vesselFactory;
    private static final String CHARACTER = "character";
    private static final String ERROR = "error";

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
    public String createCharacter(@ModelAttribute("character")CharacterDto characterDto, RedirectAttributes redirectAttributes) {
        String type = characterDto.getType();
        double capacity = characterDto.getCapacity();
        if (Arrays.stream(CharacterType.values()).noneMatch(t -> t.name().equalsIgnoreCase(type))) {
            redirectAttributes.addFlashAttribute(ERROR, "Incorrect character type");
            return "redirect:/";
        } else if (capacity <= 0) {
            redirectAttributes.addFlashAttribute(ERROR, "Stomach capacity must be greater than 0");
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute(CHARACTER, characterFactory.createCharacter(type, capacity));
        redirectAttributes.addFlashAttribute("type", type.toLowerCase());
        return "redirect:/character";
    }

}
