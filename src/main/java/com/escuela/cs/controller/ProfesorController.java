package com.escuela.cs.controller;

import com.escuela.cs.model.Grupo;
import com.escuela.cs.model.Profesor;
import com.escuela.cs.repository.GrupoRepository;
import com.escuela.cs.repository.ProfesorRepository;
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
public class ProfesorController {
    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @GetMapping("/profesores")
    public String indexProfesor(Model model) {
        model.addAttribute("profesores", this.profesorRepository.findAll());
        return "profesores/profesores-tabla";
    }

    @GetMapping("/profesores/nuevo")
    public ModelAndView formNewProfesor(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("profesores/nuevo-profe");
        mav.addObject("profesor", new Profesor());
        return mav;
    }

    @PostMapping("/profesores/nuevoprofe")
    public String guardarProfe(@Validated Profesor profesor, BindingResult result, Model model) {
        profesor.setSlug(profesor.getNombre().toLowerCase().replaceAll("'", "")
                .replaceAll(" ", "_"));
        profesorRepository.save(profesor);
        model.addAttribute("profesores", profesorRepository.findAll());
        return "redirect:/profesores";
    }

    @GetMapping("/profesores/editar/{id}")
    public String formEditProfesor(@PathVariable("id") String id, Model model) {
        Profesor profe = profesorRepository.findBySlug(id);
        model.addAttribute("profesor", profe);
        return "profesores/editar-profesor";
    }

    @PostMapping("/profesores/editar")
    public String editUser(@Validated Profesor profesor, BindingResult result, Model model) {
        profesor.setSlug(profesor.getNombre().toLowerCase().replaceAll("'", "")
                .replaceAll(" ", "_"));
        profesorRepository.save(profesor);
        model.addAttribute("profesores", profesorRepository.findAll());
        return "redirect:/profesores";
    }

    @GetMapping("/profesores/eliminar/{id}")
    public String formDeleteProfesor(@PathVariable("id") String id, Model model) {
        Profesor profe = profesorRepository.findBySlug(id);
        model.addAttribute("profesor", profe);
        return "profesores/eliminar-profesor";
    }

    @PostMapping("/profesores/eliminar")
    public String deleteUser(@Validated Profesor profesor, BindingResult result, Model model) {

        List<Grupo> grupos = grupoRepository.findByProfesor(profesor);

        for (Grupo grupo : grupos) {
            grupo.setProfesor(null);
            grupoRepository.save(grupo);
        }


        profesorRepository.delete(profesor);
        model.addAttribute("profesores", profesorRepository.findAll());
        return "redirect:/profesores";
    }

}
