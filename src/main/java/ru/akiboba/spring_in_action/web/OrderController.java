package ru.akiboba.spring_in_action.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.akiboba.spring_in_action.domain.*;
import ru.akiboba.spring_in_action.props.OrderProps;
import ru.akiboba.spring_in_action.repository.IngredientRefRepository;
import ru.akiboba.spring_in_action.repository.IngredientsRepository;
import ru.akiboba.spring_in_action.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Order_API")
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepository;
    private final IngredientRefRepository refRepository;
    private final OrderProps orderProps;
    private final IngredientsRepository ingredientsRepository;

    @Operation
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @Operation
    @GetMapping("/{id}")
    public String order(@PathVariable long id, Model model) {
        Optional<TacoOrder> order = orderRepository.findById(id);
        if (order.isPresent()) {
            model.addAttribute("tacoOrder", order.get());
        }
        return "orders";
    }

    @Operation
    @GetMapping("/taco/{id}")
    public String taco(@PathVariable long id, Model model) {
        List<String> ingredientsRef = refRepository.findAllByTaco(id).stream().map(IngredientRef::getIngredient).toList();
        List<Ingredient> ingredients = ingredientsRepository.findAll().stream().filter(ingredient -> ingredientsRef.contains(ingredient.getId())).collect(Collectors.toList());
        if (!ingredients.isEmpty()) {
            model.addAttribute("ingredients", ingredients);
        }
        return "ingredients";
    }

    @Operation
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
        return "orderForm";
        }
        order.setUser(user);
        log.info("Order submitted: {}", order);
        orderRepository.save(order);
        saveIngredientsRef(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @Operation
    @GetMapping
    public String ordersForUsers(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "ordersList";
    }

    private void saveIngredientsRef(TacoOrder order) {
        List<IngredientRef> refs = new ArrayList<>();
        order.getTacos().forEach(taco -> taco.getIngredients().forEach(ingredient -> refs.add(new IngredientRef(taco.getId(), ingredient.getId()))));
        refRepository.saveAll(refs);
    }
}
