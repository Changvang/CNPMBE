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
    public Float sum = 0.0f;
    public Integer walker = 0;
    public float res = 0.0f;

    @GetMapping("/rating/{acccode}")
    public ResponseEntity<Float> getRatingOfAcc(@PathVariable("acccode") long id) {
        try {
            List<Comment> comments = new ArrayList<Comment>();
            
            commentRepository.findAllByacccode(id).forEach(comments::add);

            if (comments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            comments.forEach((i) -> {sum += i.getRating();
                                           walker += 1; });
            res = sum/walker;
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/comments/getByUserID/{id}")
    public ResponseEntity<List<Comment>> getCommentsByUserid(@PathVariable("id") long id) {
        try {
            List<Comment> comments = new ArrayList<Comment>();
       
            commentRepository.findAllByuserid(id).forEach(comments::add);

            if (comments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/comments")
    public ResponseEntity<Comment> AddNewImageForRoom(@RequestBody Comment acc) {
        try {
            Comment _demo = commentRepository.save(new Comment(acc.getUserid(),acc.getAcccode(),acc.getRating(),acc.getContent(),acc.getPostdate()));
            return new ResponseEntity<>(_demo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/comments/{id}")
	public ResponseEntity<HttpStatus> deleteCommentByCommendID(@PathVariable("id") long id) {
		try {
			commentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment comment) {
        Optional<Comment> commentData = commentRepository.findById(id);
  
          if (commentData.isPresent()) {
            Comment _comment = commentData.get();
            _comment.setUserid(comment.getUserid());
            _comment.setAcccode(comment.getAcccode());
            _comment.setRating(comment.getRating());
            _comment.setContent(comment.getContent());
            _comment.setPostdate(comment.getPostdate());
            return new ResponseEntity<>(commentRepository.save(_comment), HttpStatus.OK);
          } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
    }
    
}
