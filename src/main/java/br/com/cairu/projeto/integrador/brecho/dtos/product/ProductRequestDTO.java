package br.com.cairu.projeto.integrador.brecho.dtos.product;

import java.util.List;

import br.com.cairu.projeto.integrador.brecho.models.Category;
import br.com.cairu.projeto.integrador.brecho.models.File;
import jakarta.annotation.Nullable;

public record ProductRequestDTO(String name, String description, String price, Boolean isActive, Category category,  @Nullable List<File> urls) {

}
