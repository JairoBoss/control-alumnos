package com.escuela.cs.repository;

import com.escuela.cs.model.Grupo;
import com.escuela.cs.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, String> {
    List<Profesor> findByNombre(String nombre);

    Profesor findBySlug(String slug);

}
