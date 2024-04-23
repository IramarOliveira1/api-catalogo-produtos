package br.com.cairu.projeto.integrador.brecho.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "product")
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "Varchar(80)")
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, columnDefinition = "Decimal(10,2)")
    private String price;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_file", nullable = false, referencedColumnName = "id")
    private File file;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category", nullable = false, referencedColumnName = "id")
    private Category category;
}
