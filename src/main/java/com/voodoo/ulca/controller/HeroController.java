package com.voodoo.ulca.controller;

import com.voodoo.ulca.service.AbilityService;
import com.voodoo.ulca.service.HeroService;
import com.voodoo.ulca.service.SynergyService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeroController {

    @Autowired
    HeroService heroService;
    @Autowired
    AbilityService abilityService;
    @Autowired
    SynergyService synergyService;

    @ResponseBody
    @RequestMapping("/getHero/{heroName}")
    public Object getHero(@PathVariable String heroName) {
        return heroService.getHero(heroName).toString();
    }

    @ResponseBody
    @RequestMapping("/getHeroAbilities/{heroName}")
    public String getHeroAbilities(@PathVariable String heroName) {
        JSONObject result = new JSONObject();
        JSONArray abilities = heroService.getHero(heroName).getJSONArray("abilities");
        for(Object json : abilities){
            result.put((String)json,abilityService.getAbility((String)json));
        }
        return result.toString();
    }

    @ResponseBody
    @RequestMapping("/getHeroSynergies/{heroName}")
    public String getHeroSynergies(@PathVariable String heroName) {
        JSONObject result = new JSONObject();
        String[] synergies = heroService.getHero(heroName).getString("keywords").split(" ");

        for (String synergy : synergies){
            result.put(synergy,synergyService.getSynergy(synergy));
        }
        return result.toString();
    }
}
