package com.voodoo.ulca.controller;

import com.voodoo.ulca.service.HeroService;
import com.voodoo.ulca.service.SynergyService;
import com.voodoo.ulca.util.StringUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class SynergyController {

    @Autowired
    SynergyService synergyService;
    @Autowired
    HeroService heroService;

    @ResponseBody
    @RequestMapping("/getSynergy/{synergyName}")
    public String getSynergy(@PathVariable String synergyName) {
        return synergyService.getSynergy(synergyName).toString();
    }

    @ResponseBody
    @RequestMapping("/getSynergyHeroes/{synergyName}")
    public String getSynergyHeroes(@PathVariable String synergyName) {
        JSONObject result = new JSONObject();
        HashMap<String, JSONObject> heroMap = heroService.getMap();
        for(String heroName : heroMap.keySet()){
            JSONObject hero = heroMap.get(heroName);
            if(hero.has("keywords") && hero.getString("keywords").contains(synergyName)){
                result.put(heroName, heroMap.get(heroName));
            }
        }
        return result.toString();
    }

    @ResponseBody
    @RequestMapping("/getSynergyType/{typeName}")
    public String getSynergyByType(@PathVariable String typeName) {
        JSONObject result = new JSONObject();
        HashMap<String, JSONObject> synergyMap = synergyService.getMap();
        for(String synergyName : synergyMap.keySet()){
            JSONObject synergy = synergyMap.get(synergyName);
            if(synergy.getString("type").equals(StringUtil.captureName(typeName))){
                result.put(synergyName.toLowerCase(), synergyMap.get(synergyName));
            }
        }
        return result.toString();
    }

}
