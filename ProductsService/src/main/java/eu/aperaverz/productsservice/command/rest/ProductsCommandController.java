package eu.aperaverz.productsservice.command.rest;

import eu.aperaverz.productsservice.command.CreateProductCommand;
import eu.aperaverz.productsservice.command.model.CreateProductRestModule;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsCommandController {
    private final CommandGateway commandGateway;

    public ProductsCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRestModule createProductRestModule) {
        var createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModule.getPrice())
                .amount(createProductRestModule.getAmount())
                .title(createProductRestModule.getTitle())
                .productId(UUID.randomUUID().toString())
                .build();

        String result;

        try {
            result = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception exception) {
            result = exception.getLocalizedMessage();
        }
        return ResponseEntity.ok().body(result);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct() {
        return ResponseEntity.ok().body("Put!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct() {
        return ResponseEntity.ok().body("Delete!");
    }
}
