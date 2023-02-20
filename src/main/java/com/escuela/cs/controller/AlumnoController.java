package com.escuela.cs.controller;

import com.escuela.cs.model.Alumno;
import com.escuela.cs.model.Calificaciones;
import com.escuela.cs.model.Grupo;
import com.escuela.cs.model.Profesor;
import com.escuela.cs.repository.AlumnoRepository;
import com.escuela.cs.repository.CalificacionRepository;
import com.escuela.cs.repository.GrupoRepository;
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
import java.util.Optional;

@Controller
public class AlumnoController {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    @GetMapping("/alumnos")
    public String indexAlumnos(Model model) {
        model.addAttribute("alumnos", this.alumnoRepository.findAll());
        return "alumnos/alumnos-tabla";
    }

    @GetMapping("/alumnos/nuevo")
    public ModelAndView formNewAlumno(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("alumnos/nuevo-alumno");
        mav.addObject("alumno", new Alumno());

        List<Grupo> grupos = grupoRepository.findAll();
        model.addAttribute("grupos", grupos);

        return mav;
    }

    @PostMapping("/alumnos/nuevoalumno")
    public String guardarAlumno(@Validated Alumno alumno, BindingResult result, Model model) {

        Grupo grupo = grupoRepository.findById(alumno.getGrupo().getId()).orElse(null);


        alumno.setGrupo(grupo);
        alumno.setSlug(alumno.getNombre().toLowerCase().replaceAll("'", "")
                .replaceAll(" ", "_"));
        alumnoRepository.save(alumno);


        model.addAttribute("alumnos", alumnoRepository.findAll());
        return "redirect:/alumnos";
    }

    @GetMapping("/alumnos/editar/{id}")
    public String formEditAlumno(@PathVariable("id") String id, Model model) {
        Alumno alumno = alumnoRepository.findBySlug(id);

        if (alumno.getGrupo() != null) {
            model.addAttribute("idGrupo", alumno.getGrupo().getId());
            alumno.setGrupo(null);
        }


        List<Grupo> grupos = grupoRepository.findAll();
        model.addAttribute("grupos", grupos);

        model.addAttribute("alumno", alumno);
        return "alumnos/editar-alumno";
    }

    @PostMapping("/alumnos/editar")
    public String editAlumno(@Validated Alumno alumno, BindingResult result, Model model) {

        Grupo grupo;
        Alumno alumnoFind = alumnoRepository.findById(alumno.getId()).orElse(null);

        if (alumno.getGrupo() != null) {
            grupo = grupoRepository.findById(alumno.getGrupo().getId()).orElse(null);

        } else {
            grupo = grupoRepository.findById(alumnoFind.getGrupo().getId()).orElse(null);

        }


        alumno.setGrupo(grupo);
        alumno.setSlug(alumno.getNombre().toLowerCase().replaceAll("'", "")
                .replaceAll(" ", "_"));
        alumnoRepository.save(alumno);
        model.addAttribute("alumnos", alumnoRepository.findAll());
        return "redirect:/alumnos";
    }


    @GetMapping("/alumnos/eliminar/{id}")
    public String formDeleteAlumno(@PathVariable("id") String id, Model model) {
        Alumno alumno = alumnoRepository.findBySlug(id);

        if (alumno.getGrupo() != null) {
            model.addAttribute("nombreGrupo", alumno.getGrupo().getNombre());
            alumno.setGrupo(null);
        }

        model.addAttribute("alumno", alumno);
        return "alumnos/eliminar-alumno";
    }


    @PostMapping("/alumnos/eliminar")
    public String deleteUser(@Validated Alumno alumno, BindingResult result, Model model) {

        List<Calificaciones> calificaciones = calificacionRepository.findByAlumno(alumno);

        calificacionRepository.deleteAll(calificaciones);


        alumnoRepository.delete(alumno);
        model.addAttribute("alumnos", alumnoRepository.findAll());
        return "redirect:/alumnos";
    }


}
