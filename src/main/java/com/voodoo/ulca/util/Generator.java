package com.voodoo.ulca.util;


import org.json.JSONObject;
import java.util.Set;

public class Generator {

    //生成heroClass
    public static void generateHero(String sourcePath, String targetPath, String packageName, String interfaceName) {
        JSONObject heroJson = JsonUtil.getJsonObjectFromFile(sourcePath);
        Set<String> keys = heroJson.keySet();
        System.out.println(keys.size()+"--keys size");
        for (String key : keys){
            String className = StringUtil.captureName(StringUtil.lineToHump(key));
            String unitClass = JsonUtil.jsonToHeroClassString(heroJson.getJSONObject(key),
                    packageName,
                    className,
                    interfaceName
                    );
            FileUtil.writeFile(targetPath + className+".java",
                    unitClass
            );
        }
    }

    public static void main(String[] args) {
        /*generateHero("src\\main\\resources\\scripts\\units.json",
                "src\\main\\java\\com\\voodoo\\ulca\\entity\\unit\\",
                "com.voodoo.ulca.entity.unit",
                "Hero"
                );
        generateSynergy();*/
    }
}
