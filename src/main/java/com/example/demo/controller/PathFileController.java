package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.*;

import com.example.demo.repository.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PathFileController {
    @Autowired
    PathFileRepository PathRepo;

    @PostMapping("/pathfiles/upload/{id}")
    public ResponseEntity<PathFile> uploadImage(@PathVariable("id") long idroom,@RequestParam("imageFile") MultipartFile file)
    throws IOException
    {
        PathFile img= new PathFile(compressBytes(file.getBytes()) ,idroom);
        PathRepo.save(img);
        return new ResponseEntity<>(img, HttpStatus.CREATED);
    }

    @GetMapping("/pathfiles/get/{id}")
    public ResponseEntity<PathFile> getPathImagesByIdRoom(@PathVariable("id") long id) {
        Optional<PathFile> img = PathRepo.findByroomID(id);
        PathFile pathFile= new PathFile(decompressBytes(img.get().getPicByte()),id);
        if (img.isPresent()) {
            return new ResponseEntity<>(pathFile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (Exception e) {

        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];

        try {

            while (!inflater.finished()) {

                int count = inflater.inflate(buffer);

                outputStream.write(buffer, 0, count);

            }

            outputStream.close();

        } catch (Exception e) {

        }

        return outputStream.toByteArray();
    }
}
