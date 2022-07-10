package eu.aperaverz.productsservice.command.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
public class CreateProductRestModule {
    @NotBlank(message = "Product title must be required field")
    private String title;
    @Min(value = 1, message = "Price cannot be less than 1")
    private BigDecimal price;
    @Min(value = 1, message = "Amount cannot be less than 1")
    @Max(value = 2000, message = "Amount cannot be more than 2000")
    private Integer amount;
}
