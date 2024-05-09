package br.com.cairu.projeto.integrador.brecho.dtos.product;

import br.com.cairu.projeto.integrador.brecho.models.Product;

public record HomeResponseDTO(Product product, Long totalProduct, Long totalCategory) {
    
}
