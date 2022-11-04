package com.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuarios")
@NamedQueries({
        @NamedQuery(name = "findByDocumento", query = "SELECT u FROM Usuario u WHERE u.documento = :documento"),
        @NamedQuery(name = "findAll", query = "SELECT u FROM Usuario u")
})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="usuarios_seq", sequenceName="usuarios_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuarios_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "documento", unique = true, nullable = false)
    private String documento;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @Column(name = "mail", nullable = false)
    private String mail;

    @ManyToOne()
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellido, String documento, String contrasenia, String mail, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.contrasenia = contrasenia;
        this.mail = mail;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", documento='" + documento + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", mail='" + mail + '\'' +
                ", rol=" + rol +
                '}';
    }
}
