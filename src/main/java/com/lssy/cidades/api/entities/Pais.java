package com.lssy.cidades.api.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pais")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Pais {
    @Id
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "nome_pt")
    private String nomePt;
    @Column(name = "sigla")
    private String sigla;

    private Integer bacen;
}
