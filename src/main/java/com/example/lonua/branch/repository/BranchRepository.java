package com.example.lonua.branch.repository;

import com.example.lonua.branch.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Optional<Branch> findByBranchNameAndBrandBrandIdx(String branchName, Integer brandIdx);

    List<Branch> findAllByBrandBrandIdx(Integer brandIdx);

}
