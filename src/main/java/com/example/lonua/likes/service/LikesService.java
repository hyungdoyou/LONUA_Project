package com.example.lonua.likes.service;


import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.entity.ProductCount;
import com.example.lonua.product.repository.ProductCountRepository;
import com.example.lonua.product.repository.ProductRepository;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final ProductRepository productRepository;
    private final ProductCountRepository productCountRepository;

    @Transactional
    public void createLikes(User user, Integer idx) {
        Optional<Product> result = productRepository.findByProductIdx(idx);

        if(result.isPresent()) {
            Product product = result.get();
            ProductCount productCount = product.getProductCount();
            productCount.increaseLikeCount();
            productCountRepository.save(productCount);
        }

    }
}
