package com.bah91.controller;

import com.bah91.domain.ImageContainer;
import com.bah91.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by bah91 on 11/08/17.
 */
@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @CrossOrigin()
    @RequestMapping("/api")
    public ArrayList<ImageContainer> getFileDetails() {
        return fileService.getFiles();
    }


}
