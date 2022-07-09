package eu.aperaverz.productsservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @PostMapping
    public ResponseEntity<String> createProduct() {
        return ResponseEntity.ok().body("HTTP POST Handled");
    }

    @GetMapping
    public ResponseEntity<String> getProduct() {
        return ResponseEntity.ok().body("Get!");
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
