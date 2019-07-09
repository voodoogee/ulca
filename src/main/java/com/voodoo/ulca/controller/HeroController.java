package com.voodoo.ulca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeroController {
    @RequestMapping("/getHero/{heroName}")
    @ResponseBody
    public Object getHero(@PathVariable String heroName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class cla = Class.forName("com.voodoo.ulca.entity.unit."+heroName );
        return cla.newInstance();
    }
}
