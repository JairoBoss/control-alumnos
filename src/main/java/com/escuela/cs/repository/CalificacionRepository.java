package com.escuela.cs.repository;

import com.escuela.cs.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificaciones, String> {

//    Calificaciones findById(String slug);

    List<Calificaciones> findByAlumno(Alumno alumno);

    List<Calificaciones> findByMateria(Materia materia);
}
