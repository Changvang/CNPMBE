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
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/comments/{acccode}")
    public ResponseEntity<List<Comment>> getAllCommentByIdRoom(@PathVariable("acccode") long id) {
        try {
            List<Comment> comments = new ArrayList<Comment>();

           

            commentRepository.findAllByacccode(id).forEach(comments::add);

            if (comments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
