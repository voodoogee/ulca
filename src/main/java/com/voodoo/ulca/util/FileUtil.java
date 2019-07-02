package com.voodoo.ulca.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class FileUtil {
    /**
     * 读取文本
     * @param pathName 文件绝对路径
     * @return 文件字符串
     */
    static String readFile(String pathName) {
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
}
