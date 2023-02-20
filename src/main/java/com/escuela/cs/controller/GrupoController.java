package com.escuela.cs.controller;

import com.escuela.cs.model.Alumno;
import com.escuela.cs.model.Grupo;
import com.escuela.cs.model.Profesor;
import com.escuela.cs.repository.AlumnoRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;


    @GetMapping("/grupos")
    public String indexGrupo(Model model) {
        model.addAttribute("grupos", this.grupoRepository.findAll());
        return "grupos/grupos-tabla";
    }

    @GetMapping("/grupos/nuevo")
    public ModelAndView formNewGrupo(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("grupo", new Grupo());
        List<Profesor> profesores = profesorRepository.findAll();
        model.addAttribute("profesores", profesores);
        mav.setViewName("grupos/nuevo-grupo");
        return mav;
    }

    @PostMapping("/grupos/nuevogrupo")
    public String guardarGrupo(@Validated Grupo grupo, BindingResult result, Model model) {
        Profesor profe = profesorRepository.findById(grupo.getProfesor().getId()).orElse(null);
        grupo.setProfesor(profe);
        grupo.setSlug(grupo.getNombre().toLowerCase().replaceAll("'", "")
                .replaceAll(" ", "_"));
        grupoRepository.save(grupo);
        model.addAttribute("grupos", this.grupoRepository.findAll());
        return "redirect:/grupos";
    }

    @GetMapping("/grupos/editar/{id}")
    public String formEditGrupo(@PathVariable("id") String id, Model model) {
        Grupo grupo = grupoRepository.findBySlug(id);


        if (grupo.getProfesor() != null) {
            model.addAttribute("idProfe", grupo.getProfesor().getId());
            grupo.setProfesor(null);
        }

        model.addAttribute("grupo", grupo);

        List<Profesor> profesores = profesorRepository.findAll();
        model.addAttribute("profesores", profesores);

        return "grupos/editar-grupo";
    }

    @PostMapping("/grupos/editar")
    public String editGrupo(@Validated Grupo grupo, BindingResult result, Model model) {

        Profesor profe;
        Grupo grupoFind = grupoRepository.findById(grupo.getId()).orElse(null);

        if (grupo.getProfesor() != null) {
            profe = profesorRepository.findById(grupo.getProfesor().getId()).orElse(null);
        } else {
            profe = profesorRepository.findById(grupoFind.getProfesor().getId()).orElse(null);
        }


        grupo.setProfesor(profe);
        grupo.setSlug(grupo.getNombre().toLowerCase().replaceAll("'", "")
                .replaceAll(" ", "_"));
        grupoRepository.save(grupo);
        model.addAttribute("grupos", this.grupoRepository.findAll());
        return "redirect:/grupos";
    }

    @GetMapping("/grupos/eliminar/{id}")
    public String formDeleteGrupo(@PathVariable("id") String id, Model model) {
        Grupo grupo = grupoRepository.findBySlug(id);

        if (grupo.getProfesor() != null) {
            model.addAttribute("nombreProfe", grupo.getProfesor().getNombre());
            grupo.setProfesor(null);
        }

        model.addAttribute("grupo", grupo);

        return "grupos/eliminar-grupo";
    }


    @PostMapping("/grupos/eliminar")
    public String deleteGrupo(@Validated Grupo grupo, BindingResult result, Model model) {

        List<Alumno> alumnos = alumnoRepository.findByGrupo(grupo);

        for (Alumno alumno : alumnos) {
            alumno.setGrupo(null);
            alumnoRepository.save(alumno);
        }

        grupoRepository.delete(grupo);
        model.addAttribute("grupos", this.grupoRepository.findAll());
        return "redirect:/grupos";
    }

}
