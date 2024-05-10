package br.com.cairu.projeto.integrador.brecho.dtos.product;

import br.com.cairu.projeto.integrador.brecho.models.File;

public record HomeResponseDTO(String name, int countClick, File file, Long totalProduct, Long totalCategory) {

}
