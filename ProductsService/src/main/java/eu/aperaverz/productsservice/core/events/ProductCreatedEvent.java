package eu.aperaverz.productsservice.core.events;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreatedEvent {
    String productId;
    String title;
    BigDecimal price;
    Integer quantity;
}
