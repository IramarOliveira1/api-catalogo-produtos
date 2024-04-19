package br.com.cairu.projeto.integrador.brecho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cairu.projeto.integrador.brecho.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsByName(String name);

    List<Category> findAllByOrderByIdDesc();
}
