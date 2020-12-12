package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.example.demo.model.PathFile;


public interface PathFileRepository extends JpaRepository<PathFile, Long>{
    List<PathFile> findAllByroomID(long roomID);
    Optional<PathFile> findByroomID(long roomID);
}