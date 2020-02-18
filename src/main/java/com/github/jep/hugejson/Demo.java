package com.github.jep.hugejson;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author jiangenping
 * 2020/2/18 下午10:10
 */

public class Demo {

    public static void main(String[] args) throws IOException {
        File file=new File("src/main/resources/hugeJson.json");
        String content= FileUtils.readFileToString(file,"UTF-8");
        System.out.println(content);
    }
}
