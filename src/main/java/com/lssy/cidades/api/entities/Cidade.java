package com.lssy.cidades.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.geo.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TypeDefs(value = {
        @TypeDef(name = "point", typeClass = PointType.class)
})
public class Cidade {

    @Id
    private Long id;
    private String nome;
    private Integer uf;
    private Integer ibge;

    @Column(name = "lat_lon")
    private String geoLocalizacao;


    @Type(type = "point")
    @Column(name = "lat_lon", updatable = false, insertable = false)
    private Point coordenadas;
}
