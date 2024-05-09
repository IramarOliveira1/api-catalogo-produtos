package br.com.cairu.projeto.integrador.brecho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cairu.projeto.integrador.brecho.dtos.product.HomeResponseDTO;
import br.com.cairu.projeto.integrador.brecho.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByIdDesc();

    List<Product> findByCategoryId(Long category_id);

    List<Product> findByNameLike(String name);

    // SELECT
    // p.name AS nome_produto,
    // f.url AS url_imagem,
    // p.count_click,
    // (SELECT COUNT(*) FROM category) AS totalCategory,
    // (SELECT COUNT(*) FROM product) AS totalProduct
    // FROM
    // category c
    // INNER JOIN
    // product p ON c.id = p.category_id
    // INNER JOIN
    // file f ON p.id = f.product_id
    // WHERE p.count_click > 0 AND p.is_active = 1
    // GROUP BY
    // p.name
    // ORDER BY p.count_click DESC

    @Query("SELECT  NEW br.com.cairu.projeto.integrador.brecho.dtos.product.HomeResponseDTO(p, (SELECT COUNT(*) FROM product) AS totalProduct, (SELECT COUNT(*) FROM category) AS totalCategory) FROM product p  WHERE p.isActive = true GROUP BY p.name ORDER BY p.countClick DESC LIMIT 7")
    List<HomeResponseDTO> countByProductAndCategory();
}
