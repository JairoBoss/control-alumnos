package com.escuela.cs.model;

import java.sql.Date;

public class AlumnosTutorDTO {

    private String nombreAlumno;
    private String ape_paternoAlumno;

    private String ape_MaternoAlumno;

    private Date fecha_nacimientoAlumno;

    private String direccionAlumno;

    private String nombreTutor;

    private String ape_paternoTutor;

    private String ape_MaternoTutor;

    private String numeroTutor;

    private String correoTutor;

    public AlumnosTutorDTO(){};

    public AlumnosTutorDTO(String nombreAlumno, String ape_paternoAlumno, String ape_MaternoAlumno, Date fecha_nacimientoAlumno, String direccionAlumno, String nombreTutor, String ape_paternoTutor, String ape_MaternoTutor, String numeroTutor, String correoTutor) {
        this.nombreAlumno = nombreAlumno;
        this.ape_paternoAlumno = ape_paternoAlumno;
        this.ape_MaternoAlumno = ape_MaternoAlumno;
        this.fecha_nacimientoAlumno = fecha_nacimientoAlumno;
        this.direccionAlumno = direccionAlumno;
        this.nombreTutor = nombreTutor;
        this.ape_paternoTutor = ape_paternoTutor;
        this.ape_MaternoTutor = ape_MaternoTutor;
        this.numeroTutor = numeroTutor;
        this.correoTutor = correoTutor;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApe_paternoAlumno() {
        return ape_paternoAlumno;
    }

    public void setApe_paternoAlumno(String ape_paternoAlumno) {
        this.ape_paternoAlumno = ape_paternoAlumno;
    }

    public String getApe_MaternoAlumno() {
        return ape_MaternoAlumno;
    }

    public void setApe_MaternoAlumno(String ape_MaternoAlumno) {
        this.ape_MaternoAlumno = ape_MaternoAlumno;
    }

    public Date getFecha_nacimientoAlumno() {
        return fecha_nacimientoAlumno;
    }

    public void setFecha_nacimientoAlumno(Date fecha_nacimientoAlumno) {
        this.fecha_nacimientoAlumno = fecha_nacimientoAlumno;
    }

    public String getDireccionAlumno() {
        return direccionAlumno;
    }

    public void setDireccionAlumno(String direccionAlumno) {
        this.direccionAlumno = direccionAlumno;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public String getApe_paternoTutor() {
        return ape_paternoTutor;
    }

    public void setApe_paternoTutor(String ape_paternoTutor) {
        this.ape_paternoTutor = ape_paternoTutor;
    }

    public String getApe_MaternoTutor() {
        return ape_MaternoTutor;
    }

    public void setApe_MaternoTutor(String ape_MaternoTutor) {
        this.ape_MaternoTutor = ape_MaternoTutor;
    }

    public String getNumeroTutor() {
        return numeroTutor;
    }

    public void setNumeroTutor(String numeroTutor) {
        this.numeroTutor = numeroTutor;
    }

    public String getCorreoTutor() {
        return correoTutor;
    }

    public void setCorreoTutor(String correoTutor) {
        this.correoTutor = correoTutor;
    }
}

