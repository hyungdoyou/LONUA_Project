package com.example.lonua.orders.model.entity;

import com.example.lonua.orders.model.request.PatchUpdateOrdersReq;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ordersIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private List<OrdersProduct> ordersProductList = new ArrayList<>();

    @Column(nullable = false, length = 50, unique = true)
    private String impUid;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

    @Column(nullable = false, length = 10)
    private String status;

    public void updateStatus(PatchUpdateOrdersReq patchUpdateOrdersReq) {
        if(patchUpdateOrdersReq.getStatus() != null) {
            this.status= patchUpdateOrdersReq.getStatus();
        }
    }

}
