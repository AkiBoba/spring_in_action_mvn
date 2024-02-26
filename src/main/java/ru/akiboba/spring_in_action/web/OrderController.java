package ru.akiboba.spring_in_action.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.akiboba.spring_in_action.domain.IngredientRef;
import ru.akiboba.spring_in_action.domain.TacoOrder;
import ru.akiboba.spring_in_action.domain.User;
import ru.akiboba.spring_in_action.repository.IngredientRefRepository;
import ru.akiboba.spring_in_action.repository.OrderRepository;
import ru.akiboba.spring_in_action.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Order_API")
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepository;
    private final IngredientRefRepository refRepository;
    private final UserRepository userRepository;

    @Operation
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @Operation
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
        return "orderForm";
        }
        user = userRepository.findByUsername(user.getUsername());
        order.setUser(user);
        log.info("Order submitted: {}", order);
        orderRepository.save(order);
        saveIngredientsRef(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    private void saveIngredientsRef(TacoOrder order) {
        List<IngredientRef> refs = new ArrayList<>();
        order.getTacos().forEach(taco -> taco.getIngredients().forEach(ingredient -> refs.add(new IngredientRef(taco.getId(), ingredient.getId()))));
        refRepository.saveAll(refs);
    }
}
