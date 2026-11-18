package com.example.sportsupplements.repository;

import com.example.sportsupplements.model.Kreatin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KreatinRepository extends CrudRepository<Kreatin, Long> {}