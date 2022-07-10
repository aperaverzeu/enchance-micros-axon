package eu.aperaverz.productsservice.query.rest;

import eu.aperaverz.productsservice.query.FindProductsQuery;
import eu.aperaverz.productsservice.query.model.ProductRestModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsQueryController {

    private final QueryGateway queryGateway;

    public ProductsQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public ResponseEntity<List<ProductRestModel>> getProducts() {
        FindProductsQuery findProductsQuery = new FindProductsQuery();

        var res = queryGateway
                .query(findProductsQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class))
                .join();

        return ResponseEntity.ok().body(res);
    }


}
