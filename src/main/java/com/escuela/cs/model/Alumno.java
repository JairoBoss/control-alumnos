package com.escuela.cs.model;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "ape_paterno", nullable = false)
    private String ape_paterno;

    @Column(name = "ape_materno", nullable = false)
    private String ape_materno;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fecha_nacimiento;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "correo_tutor", nullable = false)
    private String correo_tutor;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @OneToMany(mappedBy = "alumno",cascade = CascadeType.ALL)
    private List<Calificaciones> calificaciones;


    public Alumno(String id, String nombre, String ape_paterno, String ape_materno, String direccion, Date fecha_nacimiento, Grupo grupo, String correo_tutor) {
        this.id = id;
        this.nombre = nombre;
        this.ape_paterno = ape_paterno;
        this.ape_materno = ape_materno;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.grupo = grupo;
        this.correo_tutor = correo_tutor;
        this.slug = nombre.toLowerCase().replaceAll("'", "")
                .replaceAll(" ", "_");
    }

    public Alumno() {
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

    public String getCorreo_tutor() {
        return correo_tutor;
    }

    public void setCorreo_tutor(String correo_tutor) {
        this.correo_tutor = correo_tutor;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}
