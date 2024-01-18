package com.example.lonua.reply.repository;

import com.example.lonua.reply.model.entity.Reply;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {


    @Query("SELECT r FROM Reply r "
            + "join FETCH r.question q "
            + "join FETCH r.brand b "
            + "where r.brand.brandIdx = :brandIdx")
    List<Reply>findAllByBrandBrandIdx(Integer brandIdx);
}
