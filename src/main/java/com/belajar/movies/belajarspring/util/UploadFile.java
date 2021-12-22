package com.belajar.movies.belajarspring.util;

import com.belajar.movies.belajarspring.global.Global;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadFile {

    public Path upload(MultipartFile files, String imageFolder) throws IOException {
        var file = StringUtils.cleanPath(files.getOriginalFilename());
        var uploadFolder = Global.projectDir() +"/"+ imageFolder + "/original";
        var path = Paths.get(uploadFolder, file);
        Files.write(path, file.getBytes());
        return path;
    }
}
