package eu.aperaverz.productsservice.command.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateProductRestModule {
    private String title;
    private BigDecimal price;
    private Integer amount;
}