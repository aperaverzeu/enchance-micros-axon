package eu.aperaverz.productsservice.query.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRestModel {
    String productId;
    String title;
    BigDecimal price;
    Integer amount;
}
