package eu.aperaverz.productsservice.command;

import eu.aperaverz.productsservice.core.data.ProductLookupEntity;
import eu.aperaverz.productsservice.core.data.ProductLookupRepository;
import eu.aperaverz.productsservice.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventHandler {
    private final ProductLookupRepository productLookupRepository;

    public ProductLookupEventHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductLookupEntity productLookupEntity = ProductLookupEntity.builder()
                .productId(productCreatedEvent.getProductId())
                .title(productCreatedEvent.getTitle())
                .build();

        productLookupRepository.save(productLookupEntity);

    }
}
