package com.voodoo.ulca.util;

import java.io.*;

class FileUtil {
    /**
     * 读取文本
     * @param pathName 文件绝对路径
     * @return 文件字符串
     */
    static String readFile(String pathName) {
        String root = System.getProperty("user.dir");
        String absolutePath = root + File.separator + pathName;
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(absolutePath);
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
        String absolutePath = root + File.separator + pathName;
        try {
            File writeName = new File(absolutePath);
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
}
