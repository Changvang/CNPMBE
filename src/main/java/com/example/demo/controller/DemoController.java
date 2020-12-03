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
public class DemoController {

  @Autowired
  DemoRepository demoRepository;

  @GetMapping("/demos")
  public ResponseEntity<List<Demo>> getAllDemos(@RequestParam(required = false) String title) {
	  try {
	      List<Demo> demos = new ArrayList<Demo>();

	      if (title == null)
	        demoRepository.findAll().forEach(demos::add);
	      else
	        demoRepository.findByTitleContaining(title).forEach(demos::add);

	      if (demos.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(demos, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
  }

  @GetMapping("/demos/{id}")
  public ResponseEntity<Demo> getDemoById(@PathVariable("id") long id) {
	  Optional<Demo> demoData = demoRepository.findById(id);

	    if (demoData.isPresent()) {
	      return new ResponseEntity<>(demoData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
  }

  @PostMapping("/demos")
  public ResponseEntity<Demo> createDemo(@RequestBody Demo demo) {
	  try {
	      Demo _demo = demoRepository
	          .save(new Demo(demo.getTitle(), demo.getDescription(), false));
	      return new ResponseEntity<>(_demo, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
  }

  @PutMapping("/demos/{id}")
  public ResponseEntity<Demo> updateDemo(@PathVariable("id") long id, @RequestBody Demo demo) {
	  Optional<Demo> demoData = demoRepository.findById(id);

	    if (demoData.isPresent()) {
	      Demo _demo = demoData.get();
	      _demo.setTitle(demo.getTitle());
	      _demo.setDescription(demo.getDescription());
	      _demo.setPublished(demo.isPublished());
	      return new ResponseEntity<>(demoRepository.save(_demo), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
  }

  @DeleteMapping("/demos/{id}")
  public ResponseEntity<HttpStatus> deleteDemo(@PathVariable("id") long id) {
	  try {
	      demoRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
  }

  @DeleteMapping("/demos")
  public ResponseEntity<HttpStatus> deleteAllDemos() {
	  try {
	      demoRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
  }

  @GetMapping("/demos/published")
  public ResponseEntity<List<Demo>> findByPublished() {
	  try {
	      List<Demo> demos = demoRepository.findByPublished(true);

	      if (demos.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(demos, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
  }
}