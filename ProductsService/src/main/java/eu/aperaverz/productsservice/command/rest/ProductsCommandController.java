package eu.aperaverz.productsservice.command.rest;

import eu.aperaverz.productsservice.command.CreateProductCommand;
import eu.aperaverz.productsservice.command.model.CreateProductRestModule;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsCommandController {
    private final CommandGateway commandGateway;

    public ProductsCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@Valid @RequestBody CreateProductRestModule createProductRestModule) {
        var createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModule.getPrice())
                .amount(createProductRestModule.getAmount())
                .title(createProductRestModule.getTitle())
                .productId(UUID.randomUUID().toString())
                .build();

        String result = commandGateway.sendAndWait(createProductCommand);

        return ResponseEntity.ok().body(result);
    }
}
