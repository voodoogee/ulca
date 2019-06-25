package com.voodoo.ulca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("title","Hello MotherFucker");
        model.addAttribute("something","She moves");
        return "index";
    }
}
