package com.voodoo.ulca.util;

import org.json.JSONObject;

public class JsonUtil {

    public static JSONObject getJsonObjectFromFile(String path){
        String root = System.getProperty("user.dir");
        System.out.println(root);
        String source = FileUtil.readFile(path);
        JSONObject json = new JSONObject(source);
        json = (JSONObject)json.get("shadow_fiend");
        return json;
    }

    public static void main(String[] args) {
        System.out.println(getJsonObjectFromFile("C:\\Project\\ulca\\src\\main\\resources\\scripts\\units.json"));
    }
}
