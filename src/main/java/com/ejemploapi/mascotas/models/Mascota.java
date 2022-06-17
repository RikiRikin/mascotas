package com.ejemploapi.mascotas.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "mascotas")
@ToString @EqualsAndHashCode
public class Mascota {

    @Getter
    @Setter
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Getter @Setter @Column(name = "nombre")
    private String nombre;
    @Getter @Setter @Column(name = "edad")
    private int edad;
    @Getter @Setter @Column(name = "raza")
    private String raza;


}
