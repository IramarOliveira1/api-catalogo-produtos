package br.com.cairu.projeto.integrador.brecho.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cairu.projeto.integrador.brecho.dtos.generic.GenericResponseDTO;
import br.com.cairu.projeto.integrador.brecho.models.Product;

@Service
public class ProductService {

    public ResponseEntity<Object> register(Product product) {
        try {

            return ResponseEntity.status(201).body(new GenericResponseDTO("Categoria cadastrada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new GenericResponseDTO(e.getMessage()));
        }
    }
    
}
