package com.voodoo.ulca.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil {

    public static JSONObject getJsonObjectFromFile(String path){
        JSONObject json = new JSONObject(FileUtil.readFile(path));
        return json;
    }

    @Deprecated
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

    public static void splitJsonToFile(String sourcePath,  String targetDirect){
        JSONObject json = getJsonObjectFromFile(sourcePath);
        for (String key : json.keySet()){
            FileUtil.writeFile(targetDirect + key + ".json",json.get(key).toString());
        }
    }


}
