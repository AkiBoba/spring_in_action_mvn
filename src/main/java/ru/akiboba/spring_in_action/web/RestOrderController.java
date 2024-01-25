package ru.akiboba.spring_in_action.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.akiboba.spring_in_action.domain.Ingredient;
import ru.akiboba.spring_in_action.domain.Taco;
import ru.akiboba.spring_in_action.domain.TacoOrder;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Rest_order_API")
@Slf4j
@SessionAttributes("tacoOrder")
@RestController
@AllArgsConstructor
public class RestOrderController {

    @PostMapping("/orderadd")
    public ResponseEntity<?> processTaco(@RequestBody @Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        taco.setIngredients(getListOfIngredients(taco));
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<Ingredient> getListOfIngredients(Taco taco) {
        return taco.getIngredients().stream().filter(ingredient -> ingredient.getId() != null).collect(Collectors.toList());
    }
}