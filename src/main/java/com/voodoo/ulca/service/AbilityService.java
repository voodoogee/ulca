package com.voodoo.ulca.service;

import com.voodoo.ulca.util.FileUtil;
import com.voodoo.ulca.util.JsonUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AbilityService{
    private String pathName = "src\\main\\java\\com\\voodoo\\ulca\\entity\\ability";
    HashMap<String, JSONObject> map = new HashMap<>();
    Map<String, String> jsonPath = FileUtil.getFile(pathName, "json");

    public AbilityService(){
        for (String heroName : jsonPath.keySet()){
            JSONObject json = JsonUtil.getJsonObjectFromFile(jsonPath.get(heroName));
            map.put(heroName.substring(0,heroName.length()-5), json);
        }
    }

    public HashMap<String,JSONObject> getMap(){
        return this.map;
    }

    public JSONObject getAbility(String abilityName){
        return map.get(abilityName);
    }
}
