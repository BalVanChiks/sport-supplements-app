package com.example.sportsupplements.repository;

import com.example.sportsupplements.model.SportSupplement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportSupplementRepository extends CrudRepository<SportSupplement, Long> {}