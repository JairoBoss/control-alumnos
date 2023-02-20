package com.escuela.cs.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Tutores")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "ape_paterno", nullable = false)
    private String ape_paterno;

    @Column(name = "ape_materno", nullable = false)
    private String ape_materno;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "slug", nullable = false)
    private String slug;

//    @OneToMany(mappedBy = "tutor")
//    private List<Alumno> alumnos;

    public Tutor(String id, String nombre, String ape_paterno, String ape_materno, String numero, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.ape_paterno = ape_paterno;
        this.ape_materno = ape_materno;
        this.numero = numero;
        this.correo = correo;
        this.slug = nombre.toLowerCase().replaceAll("'", "")
                .replaceAll(" ", "_");
    }

    public Tutor() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe_paterno() {
        return ape_paterno;
    }

    public void setApe_paterno(String ape_paterno) {
        this.ape_paterno = ape_paterno;
    }

    public String getApe_materno() {
        return ape_materno;
    }

    public void setApe_materno(String ape_materno) {
        this.ape_materno = ape_materno;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}