package eu.aperaverz.productsservice.query;

import eu.aperaverz.core.events.ProductReservedEvent;
import eu.aperaverz.productsservice.core.data.ProductEntity;
import eu.aperaverz.productsservice.core.data.ProductsRepository;
import eu.aperaverz.productsservice.core.events.ProductCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@Slf4j
public class ProductEventHandler {

    private final ProductsRepository productsRepository;

    public ProductEventHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) throws Exception {
        throw exception;
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException exception) throws IllegalArgumentException {
        throw exception;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductEntity productEntity = new ProductEntity();

        BeanUtils.copyProperties(productCreatedEvent, productEntity);

        productsRepository.save(productEntity);
    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent) {
        productsRepository
                .findByProductId(productReservedEvent.getProductId())
                .ifPresent(productEntity -> {
                    productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());
                    productsRepository.save(productEntity);
                });

        log.info("ProductReservedEvent is called for productId: " + productReservedEvent.getProductId() +
                " and orderId: " + productReservedEvent.getOrderId());
    }
}
