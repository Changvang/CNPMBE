package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

 


import com.example.demo.model.Accommodation;


public interface AccommodationRepository extends JpaRepository<Accommodation, Long>{
    List<Accommodation> findByid(long ID);
    List<Accommodation> findAllByuserID(long userID);


}
