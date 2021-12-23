package com.belajar.movies.belajarspring.util;

import com.belajar.movies.belajarspring.global.Config;
import com.belajar.movies.belajarspring.global.Global;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import org.imgscalr.Scalr;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class UploadFile {

    public Path upload(MultipartFile files, String imageFolder) throws IOException {
        var file = StringUtils.cleanPath(files.getOriginalFilename());
        var uploadFolder = Global.projectDir() +"/"+ imageFolder + "/original";
        var path = Paths.get(uploadFolder, file);

        Files.copy(files.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return path;
    }

    @Async(Config.BEAN_THREAD_EXECUTOR)
    public void compressImage(Path path, String extension, int percentage, String imageFolder) throws IOException {

        var compressedFolder = Global.projectDir() + "/" + imageFolder + "/compressed";
        var uuid = UUID.randomUUID().toString() + "-" + DateUtil.dateFormat(DateUtil.FORMAT_DATEONE);
        var nameCompressedImage = path.getFileName() + "-" + uuid + "." + extension;
        var bufferedImage = ImageIO.read(path.toFile());
        var resizePercentage = 100 / percentage;
        var outputImage = Scalr.resize(bufferedImage, (bufferedImage.getHeight() / resizePercentage));
        var pathCompressedImage = Paths.get(compressedFolder, nameCompressedImage);
        var newImageFile = pathCompressedImage.toFile();

        ImageIO.write(outputImage, extension, newImageFile);
        outputImage.flush();
    }
}
