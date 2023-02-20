package com.escuela.cs.controller;

import com.escuela.cs.model.Calificaciones;
import com.escuela.cs.model.Grupo;
import com.escuela.cs.model.Materia;
import com.escuela.cs.model.Profesor;
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
public class MateriaController {
    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    @GetMapping("/materias")
    public String indexMateria(Model model) {
        model.addAttribute("materias", this.materiaRepository.findAll());
        return "materias/materias-tabla";
    }

    @GetMapping("/materias/nuevo")
    public ModelAndView formNewMateria(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("materias/nueva-materia");
        mav.addObject("materia", new Materia());
        return mav;
    }

    @PostMapping("/materias/nuevomateria")
    public String guardarMateria(@Validated Materia materia, BindingResult result, Model model) {
        materia.setSlug(materia.getNombre().toLowerCase().replaceAll("'", "")
                .replaceAll(" ", "_"));
        materiaRepository.save(materia);
        model.addAttribute("materias", materiaRepository.findAll());
        return "redirect:/materias";
    }

    @GetMapping("/materias/editar/{id}")
    public String formEditMateria(@PathVariable("id") String id, Model model) {
        Materia materia = materiaRepository.findBySlug(id);
        model.addAttribute("materia", materia);
        return "materias/editar-materia";
    }

    @PostMapping("/materias/editar")
    public String editMateria(@Validated Materia materia, BindingResult result, Model model) {
        materia.setSlug(materia.getNombre().toLowerCase().replaceAll("'", "")
                .replaceAll(" ", "_"));
        materiaRepository.save(materia);
        model.addAttribute("materias", materiaRepository.findAll());
        return "redirect:/materias";
    }


    @GetMapping("/materias/eliminar/{id}")
    public String formDeleteMateria(@PathVariable("id") String id, Model model) {
        Materia materia = materiaRepository.findBySlug(id);
        model.addAttribute("materia", materia);
        return "materias/eliminar-materia";
    }

    @PostMapping("/materias/eliminar")
    public String deleteMateria(@Validated Materia materia, BindingResult result, Model model) {

        List<Calificaciones> calificaciones = calificacionRepository.findByMateria(materia);

        calificacionRepository.deleteAll(calificaciones);

        materiaRepository.delete(materia);
        model.addAttribute("materias", materiaRepository.findAll());
        return "redirect:/materias";
    }


}
