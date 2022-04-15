package com.majian.provider2.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/upload")
public class UploadFileByLogController {


    //返回文件名
    @GetMapping("/loadFileName")
    @ResponseBody
    public List<String> loadFilesName (){
        List<String> list = new ArrayList<>();
        File file = new File("E:\\music");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            list.add(name);
        }
        return list;
    }
    //返回文件
    @GetMapping("/loadFile")
    @ResponseBody
    public List<File> loadFiles (){
        List<File> list = new ArrayList<>();
        File file = new File("E:\\music");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            //String name = files[i].getName();
            list.add(files[i]);
        }
        return list;
    }


}
