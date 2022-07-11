package eu.aperaverz.productsservice.core.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public final class ErrorMessage {
    private final Date timestamp;
    private final String message;
}
