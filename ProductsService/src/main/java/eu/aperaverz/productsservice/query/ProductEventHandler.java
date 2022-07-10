package eu.aperaverz.productsservice.query;

import eu.aperaverz.productsservice.core.data.ProductEntity;
import eu.aperaverz.productsservice.core.data.ProductsRepository;
import eu.aperaverz.productsservice.core.events.ProductCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {

    private final ProductsRepository productsRepository;

    public ProductEventHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductEntity productEntity = new ProductEntity();

        BeanUtils.copyProperties(productCreatedEvent, productEntity);

        productsRepository.save(productEntity);
    }
}
