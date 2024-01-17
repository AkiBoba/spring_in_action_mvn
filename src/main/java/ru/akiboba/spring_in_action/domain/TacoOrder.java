package ru.akiboba.spring_in_action.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * Класс содержит поля идентифицирующие характеристики заказа клиента
 */
@Data
public class TacoOrder {

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }
}