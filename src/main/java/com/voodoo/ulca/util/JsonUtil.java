package com.voodoo.ulca.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JsonUtil {

    public static JSONObject getJsonObjectFromFile(String path){
        JSONObject json = new JSONObject(FileUtil.readFile(path));
        return json;
    }

    public static String jsonToHeroClassString(JSONObject json,String packageName, String className, String interfaceName){
        String result;
        StringBuffer sbf = new StringBuffer();
        sbf.append("public class "+className+" implements " +interfaceName+ "{\r\n");
        for(String key : json.keySet()){
            Object value = json.get(key);
            sbf.append("\tpublic final ");
            if(value instanceof String){
                sbf.append("String "+ key + " = \"" + value + "\"" + ";\r\n");
            }else if(value instanceof JSONArray){
                String tempValue = value.toString().replace("[","{").replace("]","}");
                if(((JSONArray) value).length() > 0){
                    if(((JSONArray) value).get(0) instanceof String){
                        sbf.append("String[] "+ key + " = " + tempValue + ";\r\n");
                    }else if(((JSONArray) value).get(0) instanceof Boolean){
                        sbf.append("boolean[] "+ key + " = " + tempValue + ";\r\n");
                    }else{
                        sbf.append("double[] "+ key + " = " + tempValue + ";\r\n");
                    }
                }
            }else if(value instanceof Boolean){
                sbf.append("boolean "+ key + " = " + value + ";\r\n");
            }else{
                sbf.append("double "+ key + " = " + value + ";\r\n");
            }
        }
        sbf.append("}\r\n");
        result = sbf.toString();
        String header = "package "+packageName+";\r\n";
        header += "import com.voodoo.ulca.entity."+ interfaceName +";\r\n";
        return header + result;
    }

    public static void generateSynergy(JSONObject json, String className){
        String result = "";
        StringBuffer sbf = new StringBuffer();
        sbf.append("public class "+ className +" {\r\n");
        for (String key : json.keySet()){

            Object value = json.get(key);
            sbf.append("\tpublic final ");
            if(key.equals("levels")){
                JSONArray levels = (JSONArray)value;
                for(int i = 0; i < levels.length(); i++){

                }
                //sbf.append("Level[] " + key + " = \"" + value + "\"" + ";\r\n");
            }else {
                if (value instanceof String) {
                    sbf.append("String " + key + " = \"" + value + "\"" + ";\r\n");
                } else if (value instanceof JSONArray) {
                    String tempValue = value.toString().replace("[", "{").replace("]", "}");
                    if (((JSONArray) value).length() > 0) {
                        if (((JSONArray) value).get(0) instanceof String) {
                            sbf.append("String[] " + key + " = " + tempValue + ";\r\n");
                        } else if (((JSONArray) value).get(0) instanceof Boolean) {
                            sbf.append("boolean[] " + key + " = " + tempValue + ";\r\n");
                        } else {
                            sbf.append("double[] " + key + " = " + tempValue + ";\r\n");
                        }
                    }
                } else if (value instanceof Boolean) {
                    sbf.append("boolean " + key + " = " + value + ";\r\n");
                } else {
                    sbf.append("double " + key + " = " + value + ";\r\n");
                }
            }
        }
        sbf.append("}\r\n");

    }
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    public static String lineToHump(String str){
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}
