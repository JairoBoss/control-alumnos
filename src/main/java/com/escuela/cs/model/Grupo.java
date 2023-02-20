package com.escuela.cs.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion", nullable = true)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "profesor_id", nullable = true)
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private Profesor profesor;

    @Column(name = "slug", nullable = true)
    private String slug;

    @OneToMany(mappedBy = "grupo")
    private List<Alumno> alumnos;

    public Grupo(String id, String nombre, Profesor profesor, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.profesor = profesor;
        this.descripcion = descripcion;
        this.slug = nombre.toLowerCase().replaceAll("'", "")
                .replaceAll(" ", "_");
    }

    public Grupo() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", profesor=" + profesor +
                ", slug='" + slug + '\'' +
                ", alumnos=" + alumnos +
                '}';
    }
}
