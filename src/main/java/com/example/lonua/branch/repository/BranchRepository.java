package com.example.lonua.branch.repository;

import com.example.lonua.branch.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
}
