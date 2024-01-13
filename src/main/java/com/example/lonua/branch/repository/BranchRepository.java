package com.example.lonua.branch.repository;

import com.example.lonua.branch.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Optional<Branch>findBybranchName(String branchName);
}
