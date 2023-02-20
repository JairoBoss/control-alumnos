package com.escuela.cs.controller;

import com.escuela.cs.model.*;
import com.escuela.cs.repository.AlumnoRepository;
import com.escuela.cs.repository.CalificacionRepository;
import com.escuela.cs.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CalificacionController {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @GetMapping("/calificaciones")
    public String indexCalificaiones(Model model) {
        model.addAttribute("calificaciones", this.calificacionRepository.findAll());
        return "calificaciones/calificaciones-tabla";
    }


    @GetMapping("/calificaciones/nuevo")
    public ModelAndView formNewCalificacion(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("calificacion", new Calificaciones());

        List<Alumno> alumnos = alumnoRepository.findAll();
        model.addAttribute("alumnos", alumnos);

        List<Materia> materias = materiaRepository.findAll();
        model.addAttribute("materias", materias);

        mav.setViewName("calificaciones/nuevo-calificacion");
        return mav;
    }

    @PostMapping("/calificaciones/nuevocalificacion")
    public String guardarCalificacion(@Validated Calificaciones calificacion, BindingResult result, Model model) {
        Materia materia = materiaRepository.findById(calificacion.getMateria().getId()).orElse(null);
        Alumno alumno = alumnoRepository.findById(calificacion.getAlumno().getId()).orElse(null);

        calificacion.setMateria(materia);
        calificacion.setAlumno(alumno);

        calificacionRepository.save(calificacion);
        model.addAttribute("calificaciones", this.calificacionRepository.findAll());
        return "redirect:/calificaciones";
    }

    @GetMapping("/calificaciones/editar/{id}")
    public String formEditCalificacion(@PathVariable("id") String id, Model model) {

        Calificaciones calificacion = calificacionRepository.findById(id).orElse(null);

        if (calificacion.getAlumno() != null) {
            model.addAttribute("idAlumno", calificacion.getAlumno().getId());
            calificacion.setAlumno(null);
        }

        if (calificacion.getMateria() != null) {
            model.addAttribute("idMateria", calificacion.getMateria().getId());
            calificacion.setMateria(null);
        }

        model.addAttribute("calificacion", calificacion);

        List<Alumno> alumnos = alumnoRepository.findAll();
        model.addAttribute("alumnos", alumnos);

        List<Materia> materias = materiaRepository.findAll();
        model.addAttribute("materias", materias);

        return "calificaciones/editar-calificacion";
    }


    @PostMapping("/calificaciones/editar")
    public String editCalificacion(@Validated Calificaciones calificacion, BindingResult result, Model model) {
        Materia materia;
        Alumno alumno;
        Calificaciones calificacionFind = calificacionRepository.findById(calificacion.getId()).orElse(null);

        if (calificacion.getMateria() != null) {
            materia = materiaRepository.findById(calificacion.getMateria().getId()).orElse(null);
        } else {
            materia = materiaRepository.findById(calificacionFind.getMateria().getId()).orElse(null);
        }

        if (calificacion.getAlumno() != null) {
            alumno = alumnoRepository.findById(calificacion.getAlumno().getId()).orElse(null);
        } else {
            alumno = alumnoRepository.findById(calificacionFind.getAlumno().getId()).orElse(null);
        }


        calificacion.setMateria(materia);
        calificacion.setAlumno(alumno);

        calificacionRepository.save(calificacion);
        model.addAttribute("calificaciones", this.calificacionRepository.findAll());
        return "redirect:/calificaciones";
    }


    @GetMapping("/calificaciones/eliminar/{id}")
    public String formDeleteCalificacion(@PathVariable("id") String id, Model model) {

        Calificaciones calificacion = calificacionRepository.findById(id).orElse(null);

        if (calificacion.getAlumno() != null) {
            model.addAttribute("alumnoNombre", calificacion.getAlumno().getNombre());
            calificacion.setAlumno(null);
        }

        if (calificacion.getMateria() != null) {
            model.addAttribute("materiaNombre", calificacion.getMateria().getNombre());
            calificacion.setMateria(null);
        }

        model.addAttribute("calificacion", calificacion);

        return "calificaciones/eliminar-calificacion";
    }

    @PostMapping("/calificaciones/eliminar")
    public String deleteUser(@Validated Calificaciones calificacion, BindingResult result, Model model) {

        calificacionRepository.delete(calificacion);
        model.addAttribute("calificaciones", this.calificacionRepository.findAll());
        return "calificaciones/calificaciones-tabla";
    }


}
