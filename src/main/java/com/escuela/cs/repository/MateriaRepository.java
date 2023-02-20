package com.escuela.cs.repository;

import com.escuela.cs.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, String> {
    Materia findBySlug(String slug);

}
