package eu.aperaverz.productsservice.query;

import eu.aperaverz.productsservice.core.data.ProductsRepository;
import eu.aperaverz.productsservice.query.model.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsQueryHandler {
    private final ProductsRepository productsRepository;

    public ProductsQueryHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @QueryHandler
    public List<ProductRestModel> getProducts(FindProductsQuery findProductsQuery) {
        List<ProductRestModel> productRestModels = new ArrayList<>();

        productsRepository
                .findAll()
                .forEach(storedProduct -> {
                    ProductRestModel productRestModel = new ProductRestModel();
                    BeanUtils.copyProperties(storedProduct, productRestModel);
                    productRestModels.add(productRestModel);
                });

        return productRestModels;
    }
}
