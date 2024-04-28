package br.com.cairu.projeto.integrador.brecho.dtos.product;

import br.com.cairu.projeto.integrador.brecho.models.Category;

public record ProductRequestDTO(String name, String description, String price, Boolean isActive, Category category) {

}
