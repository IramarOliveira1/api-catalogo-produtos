package br.com.cairu.projeto.integrador.brecho.dtos.user;

public record UserResponseDTO(Long id, String name, String email, String cpf, String phone, Boolean isAdmin) {

}
