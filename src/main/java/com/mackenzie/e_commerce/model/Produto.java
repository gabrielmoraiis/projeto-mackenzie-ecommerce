package com.mackenzie.e_commerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(precision = 10, scale = 2)
    private BigDecimal preco;

    private String urlImagem;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
            name = "produto_essencias",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "essencia_id")
    )
    private List<Essencia> essenciasDisponiveis;

    @ManyToMany
    @JoinTable(
            name = "produto_opcoes_adicionais",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "opcao_adicional_id")
    )
    private List<OpcaoAdicional> opcoesAdicionais;

}
