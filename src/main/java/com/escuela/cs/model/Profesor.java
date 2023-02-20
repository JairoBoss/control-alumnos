package com.escuela.cs.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Profesores")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @Column(name = "nombre",nullable = true)
    private String nombre;


    @Column(name = "ape_paterno",nullable = true)
    private String ape_paterno;

    @Column(name = "ape_materno",nullable = true)
    private String ape_materno;

    @Column(name = "fecha_nacimiento",nullable = true)
    private Date fecha_nacimiento;

    @Column(name = "direccion",nullable = true)
    private String direccion;

    @Column(name = "correo",nullable = true)
    private String correo;

    @Column(name = "telefono",nullable = true)
    private String telefono;

    @Column(name = "password",nullable = true)
    private String password;

    @Column(name = "slug",nullable = true)
    private String slug;

    @OneToMany(mappedBy = "profesor")
    private List<Grupo> grupos;

    public Profesor(String id, String nombre, String ape_paterno, String ape_materno, Date fecha_nacimiento, String direccion, String correo, String telefono, String password) {
        this.id = id;
        this.nombre = nombre;
        this.ape_paterno = ape_paterno;
        this.ape_materno = ape_materno;
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
        this.slug = nombre.toLowerCase().replaceAll("'", "")
                .replaceAll(" ", "_");
    }

    public Profesor(String id, String nombre, String ape_paterno, String ape_materno, Date fecha_nacimiento, String direccion, String correo, String telefono, String password, String slug,List<Grupo> grupos) {
        this.id = id;
        this.nombre = nombre;
        this.ape_paterno = ape_paterno;
        this.ape_materno = ape_materno;
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
        this.slug = slug;
        this.grupos = grupos;
        this.slug = nombre.toLowerCase().replaceAll("'", "")
                .replaceAll(" ", "_");
    }

    public Profesor() {

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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ape_paterno='" + ape_paterno + '\'' +
                ", ape_materno='" + ape_materno + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", password='" + password + '\'' +
                ", slug='" + slug + '\'' +
                ", grupos=" + grupos +
                '}';
    }
}
