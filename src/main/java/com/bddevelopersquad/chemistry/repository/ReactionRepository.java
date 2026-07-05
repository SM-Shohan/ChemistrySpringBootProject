package com.bddevelopersquad.chemistry.repository;

import com.bddevelopersquad.chemistry.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
}
