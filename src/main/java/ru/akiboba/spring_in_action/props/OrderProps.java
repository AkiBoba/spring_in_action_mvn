package ru.akiboba.spring_in_action.props;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
public class OrderProps {

    @Min(value = 1, message = "must be between 1 and 25")
    @Max(value = 25, message = "must be between 1 and 25")
    private int pageSize = 20;
}
