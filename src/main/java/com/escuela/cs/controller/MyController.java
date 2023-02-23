package com.escuela.cs.controller;

import com.escuela.cs.model.Alumno;
import com.escuela.cs.model.Grupo;
import com.escuela.cs.model.UsuarioLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MyController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/inicio")
    public String homeInicio() {
        return "inicio";
    }


    @GetMapping("/login")
    public ModelAndView formLogin(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        mav.addObject("login", new UsuarioLogin());

        return mav;
    }


    @PostMapping("/login")
    public String guardarAlumno(@Validated UsuarioLogin usuarioLogin, BindingResult result, Model model) {

        System.out.println(usuarioLogin.getNombreUsuario());
        if (usuarioLogin.getNombreUsuario().equals("admin@gmail.com")){
            return "redirect:/";
        }

        return "redirect:/login";

    }


}
