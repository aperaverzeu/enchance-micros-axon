package eu.aperaverz.productsservice.command;

import eu.aperaverz.productsservice.core.events.ProductCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {
    @AggregateIdentifier
    String productId;
    String title;
    BigDecimal price;
    Integer amount;

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        if (command.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price cannot be zero!");
        }

        if (command.getTitle() == null ||
                command.getTitle().isEmpty() ||
                command.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty!");
        }

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();

        BeanUtils.copyProperties(command, productCreatedEvent);

        AggregateLifecycle.apply(productCreatedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.productId = productCreatedEvent.getProductId();
        this.title = productCreatedEvent.getTitle();
        this.price = productCreatedEvent.getPrice();
        this.amount = productCreatedEvent.getAmount();
    }
}
