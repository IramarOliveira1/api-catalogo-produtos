package br.com.cairu.projeto.integrador.brecho.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cairu.projeto.integrador.brecho.config.security.TokenService;
import br.com.cairu.projeto.integrador.brecho.dtos.generic.GenericResponseDTO;
import br.com.cairu.projeto.integrador.brecho.dtos.user.UserResponseDTO;
import br.com.cairu.projeto.integrador.brecho.models.User;
import br.com.cairu.projeto.integrador.brecho.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService jwtService;

    public ResponseEntity<Object> register(User user) {
        try {

            if (userRepository.existsByCpf(user.getCpf())) {
                throw new Exception("CPF já existe!");
            }

            if (userRepository.existsByEmail(user.getEmail())) {
                throw new Exception("E-mail já existe!");
            }

            if (userRepository.existsByPhone(user.getPhone())) {
                throw new Exception("Telefone já existe!");
            }

            String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

            user.setPassword(encryptedPassword);
            user.setEmail(user.getEmail().toLowerCase().trim());

            userRepository.save(user);

            return ResponseEntity.status(201).body(new GenericResponseDTO("Usuário cadastrado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new GenericResponseDTO(e.getMessage()));
        }
    }

    public ResponseEntity<Object> all() {
        List<UserResponseDTO> users = userRepository.findAllByOrderByIdDesc();

        return ResponseEntity.status(200).body(users);
    }

    public ResponseEntity<Object> index(Long id) {
        Optional<User> user = userRepository.findById(id);

        UserResponseDTO userResponseRecord = new UserResponseDTO(
                user.get().getId(), user.get().getName(),
                user.get().getEmail(), user.get().getCpf(),
                user.get().getPhone(), user.get().isAdmin());

        return ResponseEntity.status(200).body(userResponseRecord);
    }

    public ResponseEntity<Object> delete(Long id) {
        userRepository.deleteById(id);

        return ResponseEntity.status(200).body(new GenericResponseDTO("Usuário excluído com sucesso!"));
    }

    public ResponseEntity<Object> update(Long id, User user) {
        try {
            User userUpdate = userRepository.findById(id).get();

            if (!user.getCpf().equals(userUpdate.getCpf()) && userRepository.existsByCpf(user.getCpf())) {
                throw new Exception("CPF já existe!");
            }

            if (!user.getEmail().equals(userUpdate.getEmail()) && userRepository.existsByEmail(user.getEmail())) {
                throw new Exception("E-mail já existe!");
            }

            if (!user.getPhone().equals(userUpdate.getPhone())
                    && userRepository.existsByPhone(user.getPhone())) {
                throw new Exception("Telefone já existe!");
            }

            userUpdate.setName(user.getName());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setCpf(user.getCpf());
            userUpdate.setPassword(user.getPassword() == null ? userUpdate.getPassword()
                    : new BCryptPasswordEncoder().encode(user.getPassword()));
            userUpdate.setPhone(user.getPhone());
            userUpdate.setAdmin(user.isAdmin());

            userRepository.save(userUpdate);

            return ResponseEntity.status(200).body(new GenericResponseDTO("Usuário atualizado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new GenericResponseDTO(e.getMessage()));
        }
    }
}
