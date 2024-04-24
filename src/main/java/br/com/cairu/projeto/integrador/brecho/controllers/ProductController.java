package br.com.cairu.projeto.integrador.brecho.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

}
