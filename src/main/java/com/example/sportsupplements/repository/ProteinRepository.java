package com.example.sportsupplements.repository;

import com.example.sportsupplements.model.Protein;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProteinRepository extends CrudRepository<Protein, Long> {}