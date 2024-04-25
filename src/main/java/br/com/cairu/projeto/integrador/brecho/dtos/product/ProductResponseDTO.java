package br.com.cairu.projeto.integrador.brecho.dtos.product;

import br.com.cairu.projeto.integrador.brecho.models.Category;
import br.com.cairu.projeto.integrador.brecho.models.File;

public record ProductResponseDTO(Long id, String name, String description, String price, Category category) {

}
