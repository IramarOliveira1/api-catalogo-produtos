package br.com.cairu.projeto.integrador.brecho.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cairu.projeto.integrador.brecho.dtos.product.ProductRequestDTO;
import br.com.cairu.projeto.integrador.brecho.models.File;
import br.com.cairu.projeto.integrador.brecho.models.Product;
import br.com.cairu.projeto.integrador.brecho.services.ProductService;
import jakarta.annotation.Nullable;

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
    public ResponseEntity<Object> index(@PathVariable("id") Long id, @RequestParam("detail") String detail) {
        return productService.index(id, detail);
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
            @Nullable @RequestPart("images") ArrayList<MultipartFile> images,
            @Nullable @RequestPart("urls") List<File> url) {

        // return productService.update(id, data, images, url);
        return productService.update(id, data, images, url);
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
