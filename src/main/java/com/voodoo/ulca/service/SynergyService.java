package com.voodoo.ulca.service;

import com.voodoo.ulca.util.FileUtil;
import com.voodoo.ulca.util.JsonUtil;
import com.voodoo.ulca.util.StringUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SynergyService {
    private String pathName = "src\\main\\java\\com\\voodoo\\ulca\\entity\\synergy";
    HashMap<String, JSONObject> map = new HashMap<>();
    Map<String, String> jsonPath = FileUtil.getFile(pathName, "json");
    public SynergyService(){
        for (String heroName : jsonPath.keySet()){
            JSONObject json = JsonUtil.getJsonObjectFromFile(jsonPath.get(heroName));
            map.put(heroName.substring(0,heroName.length()-5), json);
        }
    }

    public HashMap<String,JSONObject> getMap(){
        return this.map;
    }

    public JSONObject getSynergy(String synergyName){
        return map.get(StringUtil.captureName(synergyName));
    }
}
