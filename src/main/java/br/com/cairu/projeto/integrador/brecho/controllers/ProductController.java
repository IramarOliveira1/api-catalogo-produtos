package br.com.cairu.projeto.integrador.brecho.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cairu.projeto.integrador.brecho.dtos.product.ProductRequestDTO;
import br.com.cairu.projeto.integrador.brecho.models.Product;
import br.com.cairu.projeto.integrador.brecho.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestPart("data") String data,
            @RequestPart("images") ArrayList<MultipartFile> images) {

        return productService.register(data, images);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> all() {
        return productService.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> index(@PathVariable("id") Long id) {
        return productService.index(id);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Object> productsByCategory(@PathVariable("id") Long category_id) {
        return productService.getByCategory(category_id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        return productService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestPart("data") ProductRequestDTO data,
            @RequestPart("images") ArrayList<MultipartFile> images) {
        return productService.update(id, data, images);
    }

    @PostMapping("/filter")
    public ResponseEntity<Object> filter(@RequestBody Product name) {
        return productService.filter(name);
    }

    @GetMapping("/home/all")
    public ResponseEntity<Object> home() {
        return productService.home();
    }
}
