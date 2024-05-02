package br.com.cairu.projeto.integrador.brecho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.cairu.projeto.integrador.brecho.dtos.user.UserResponseDTO;
import br.com.cairu.projeto.integrador.brecho.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);

    List<UserResponseDTO> findAllByOrderByIdDesc();

    Boolean existsByCpf(String cpf);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);
}
