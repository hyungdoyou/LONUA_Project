package com.example.lonua.likes.repository;

import com.example.lonua.likes.model.entity.Likes.Likes;
import com.example.lonua.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Integer> {
    public List<Likes> findAllByUser(User user);
    public Integer deleteByLikesIdxAndUser(Integer idx, User user);
}
