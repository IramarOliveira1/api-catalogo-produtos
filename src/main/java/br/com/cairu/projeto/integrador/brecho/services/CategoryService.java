package br.com.cairu.projeto.integrador.brecho.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cairu.projeto.integrador.brecho.dtos.generic.GenericResponseDTO;
import br.com.cairu.projeto.integrador.brecho.models.Category;
import br.com.cairu.projeto.integrador.brecho.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<Object> register(Category category) {
        try {

            if (categoryRepository.existsByName(category.getName())) {
                throw new Exception("Nome já existe!");
            }

            categoryRepository.save(category);

            return ResponseEntity.status(201).body(new GenericResponseDTO("Categoria cadastrada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new GenericResponseDTO(e.getMessage()));
        }
    }

    public ResponseEntity<Object> all() {
        List<Category> categories = categoryRepository.findAllByOrderByIdDesc();

        return ResponseEntity.status(200).body(categories);
    }

    public ResponseEntity<Object> index(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        return ResponseEntity.status(200).body(category);
    }

    public ResponseEntity<Object> delete(Long id) {
        try {
            categoryRepository.deleteById(id);

            return ResponseEntity.status(200).body(new GenericResponseDTO("Categoria deletada com sucesso!"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return ResponseEntity.status(400)
                    .body(new GenericResponseDTO(
                            "Não é possível deletar a categoria pois ela está associada a um ou mais produtos."));
        }
    }

    public ResponseEntity<Object> update(Long id, Category category) {
        try {

            Category categoryUpdate = categoryRepository.findById(id).get();

            if (!category.getName().equals(categoryUpdate.getName())
                    && categoryRepository.existsByName(category.getName())) {
                throw new Exception("Nome já existe!");
            }

            categoryUpdate.setName(category.getName());

            categoryRepository.save(categoryUpdate);

            return ResponseEntity.status(200).body(new GenericResponseDTO("Categoria atualizada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new GenericResponseDTO(e.getMessage()));
        }
    }

}
