package br.com.cairu.projeto.integrador.brecho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cairu.projeto.integrador.brecho.models.File;

public interface FileRepository extends JpaRepository<File, Long> {
        List<File> findByProductId(Long id);
}
