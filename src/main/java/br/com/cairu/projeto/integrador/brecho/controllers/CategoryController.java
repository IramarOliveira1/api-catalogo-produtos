package br.com.cairu.projeto.integrador.brecho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cairu.projeto.integrador.brecho.models.Category;
import br.com.cairu.projeto.integrador.brecho.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Category user) {
        return categoryService.register(user);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> all() {
        return categoryService.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> index(@PathVariable("id") Long id) {
        return categoryService.index(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        return categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }


}
