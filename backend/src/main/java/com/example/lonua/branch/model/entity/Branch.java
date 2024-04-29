package com.example.lonua.branch.model.entity;

import com.example.lonua.brand.model.entity.Brand;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer branchIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Brand_idx")
    private Brand brand;

    @Column(nullable = true, length = 30, unique = true)
    private String branchName;

    @Column(nullable = true, length = 100, unique = true)
    private String branchAddress;
}
