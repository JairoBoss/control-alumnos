package com.escuela.cs.repository;

import com.escuela.cs.model.Grupo;
import com.escuela.cs.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, String> {
    List<Grupo> findByNombre(String nombre);

    Grupo findBySlug(String slug);

    List<Grupo> findByProfesor(Profesor profesor);

}
