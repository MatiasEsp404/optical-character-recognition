package com.matias.ocr.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@RestController
public class ImageController {
 
    @PostMapping("/image")
    public String recognizeText(@RequestParam("image") MultipartFile image) throws IOException, TesseractException {
        File convFile = new File(image.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(image.getBytes());
        fos.close();
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:/Users/Usuario/Documents/GitHub/optical-character-recognition/etc/training-files");
        tesseract.setLanguage("spa");
        String result = tesseract.doOCR(convFile);
        System.out.println(result); 
        return result;
    }
}
