package com.voodoo.ulca.util;

import java.io.*;
import java.util.HashMap;

public class FileUtil {
    /**
     * 读取文本
     * @param pathName 文件绝对路径
     * @return 文件字符串
     */
    static String readFile(String pathName) {
        String root = System.getProperty("user.dir");
        if(pathName.indexOf(root) < 0){
            pathName = root + File.separator + pathName;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(pathName);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 写文本
     * @param pathName 文件绝对路径
     * @param content 文本内容
     */
    public static void writeFile(String pathName, String content){
        String root = System.getProperty("user.dir");
        if(pathName.indexOf(root) < 0){
            pathName = root + File.separator + pathName;
        }
        try {
            File writeName = new File(pathName);
            writeName.createNewFile();
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)){
                out.write(content);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件夹内所有文件名
     * @param path 目录路径
     * @param suffix 文件后缀
     * @return
     */

    public static HashMap<String,String> getFile(String pathName, String suffix) {
        String root = System.getProperty("user.dir");
        if(pathName.indexOf(root) < 0){
            pathName = root + File.separator + pathName;
        }
        File file = new File(pathName);
        File[] array = file.listFiles();
        HashMap<String,String> result = new HashMap<String,String>();
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile()){
                if (array[i].getPath().endsWith("." + suffix)){
                    result.put(array[i].getName(),array[i].getPath());
                }else {
                    continue;
                }
            } else if (array[i].isDirectory()){
                result.putAll(getFile(array[i].getPath(),suffix));
            }
        }
        return result;
    }
}
