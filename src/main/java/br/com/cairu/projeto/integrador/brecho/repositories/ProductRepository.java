package br.com.cairu.projeto.integrador.brecho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cairu.projeto.integrador.brecho.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByIdDesc();

    List<Product> findByCategoryId(Long category_id);

    List<Product> findByNameLike(String name);
}
