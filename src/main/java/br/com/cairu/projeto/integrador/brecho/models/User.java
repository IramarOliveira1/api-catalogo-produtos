package br.com.cairu.projeto.integrador.brecho.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Entity(name = "user")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "Varchar(80)")
    private String name;

    @Column(nullable = false, columnDefinition = "Varchar(80)")
    private String email;

    @Column(nullable = false, columnDefinition = "Varchar(14)")
    private String cpf;

    @Column(nullable = false, columnDefinition = "Varchar(80)")
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;

    @Column(nullable = false, columnDefinition = "Varchar(15)")
    private String phone;
}
