package ru.akiboba.spring_in_action.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.akiboba.spring_in_action.domain.Ingredient;
import ru.akiboba.spring_in_action.domain.Taco;
import ru.akiboba.spring_in_action.domain.TacoOrder;
import ru.akiboba.spring_in_action.repository.IngredientTypeRepository;

@Tag(name = "Design_API")
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    private final IngredientTypeRepository typeRepository;

    /**
     * Этот метод используется для добавления в модель атрибутов со значениями ингредиентов
     * @param model контейнер в котором будут сохранены аргументы для отправки их веб-представлению.
     */
    @Operation
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        model.addAttribute("types", typeRepository.findAll());
    }

    @Operation
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "ingredient")
    public Ingredient ingredient() {
        return new Ingredient();
    }

    @Operation
    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @Operation
    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

}
