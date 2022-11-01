package com.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="usuarios_seq", sequenceName="usuarios_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuarios_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "documento")
    private String documento;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "mail")
    private String mail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
}
