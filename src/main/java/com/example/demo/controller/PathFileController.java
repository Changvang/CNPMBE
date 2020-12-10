package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.*;

import com.example.demo.repository.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PathFileController {
    @Autowired
    PathFileRepository PathRepo;

    @GetMapping("/pathfiles/{id}")
    public ResponseEntity<List<PathFile>> getPathImagesByIdRoom(@PathVariable("id") long id) {
        try {
            List<PathFile> pathFile = new ArrayList<PathFile>();
            PathRepo.findAllByroomID(id).forEach(pathFile::add);
            if (pathFile.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pathFile, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pathfiles")
    public ResponseEntity<PathFile> AddNewImageForRoom(@RequestBody PathFile acc) {
        try {
            PathFile _demo = PathRepo.save(new PathFile(acc.getPath(),acc.getRoomID()));
            return new ResponseEntity<>(_demo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
