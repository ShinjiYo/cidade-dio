package com.lssy.cidades.api.entities;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "estado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Estado {

    @Id
    private Long id;
    private String nome;
    private String uf;
    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "id")
    private Pais pais;
    private Integer ibge;
    @Type(type = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    @Column(name="ddd", columnDefinition = "jsonb")
    private List<Integer> ddd;

}
