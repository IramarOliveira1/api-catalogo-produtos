package br.com.cairu.projeto.integrador.brecho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cairu.projeto.integrador.brecho.dtos.product.TotalCountsDTO;
import br.com.cairu.projeto.integrador.brecho.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByIdDesc();

    List<Product> findByIsActiveTrueOrderByIdDesc();

    List<Product> findByCategoryIdAndIsActiveTrue(Long category_id);

    List<Product> findByCategoryId(Long category_id);

    List<Product> findByNameLike(String name);

    @Query("SELECT NEW br.com.cairu.projeto.integrador.brecho.dtos.product.TotalCountsDTO(COUNT(p), (SELECT COUNT(c) FROM category c)) FROM product p")
    TotalCountsDTO countTotalProductsAndCategories();

    @Query("SELECT p FROM product p ORDER BY p.countClick DESC LIMIT 15")
    List<Product> findCategoryProduct();
}
