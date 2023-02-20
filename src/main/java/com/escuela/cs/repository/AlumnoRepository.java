package com.escuela.cs.repository;

import com.escuela.cs.model.Alumno;
import com.escuela.cs.model.Grupo;
import com.escuela.cs.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, String> {
//    List<Alumno> findByNombre(String nombre);

    Alumno findBySlug(String slug);

    List<Alumno> findByGrupo(Grupo grupo);

}
