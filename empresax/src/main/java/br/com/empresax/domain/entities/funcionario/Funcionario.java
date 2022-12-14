package br.com.empresax.domain.entities.funcionario;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;

    @Column(name = "nome", length = 50, nullable = false)
    protected String nome;

    @Column(name = "mes_ano_admissao", nullable = false)
    public LocalDate mesAnoAdmissao;

    @Column(name = "cargo", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    protected CargoEnum cargo;
}
