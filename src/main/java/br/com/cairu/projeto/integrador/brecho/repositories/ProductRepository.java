package br.com.cairu.projeto.integrador.brecho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cairu.projeto.integrador.brecho.dtos.product.HomeResponseDTO;
import br.com.cairu.projeto.integrador.brecho.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByIdDesc();

    List<Product> findByIsActiveTrueOrderByIdDesc();

    List<Product> findByCategoryIdAndIsActiveTrue(Long category_id);

    List<Product> findByCategoryId(Long category_id);

    List<Product> findByNameLike(String name);

    @Query("SELECT  NEW br.com.cairu.projeto.integrador.brecho.dtos.product.HomeResponseDTO(p.name, p.countClick, p.files, p.isActive, (SELECT COUNT(*) FROM product) AS totalProduct, (SELECT COUNT(*) FROM category) AS totalCategory) FROM product p GROUP BY p.name ORDER BY p.countClick DESC LIMIT 15")
    List<HomeResponseDTO> findCategoryProductCounts();
}
