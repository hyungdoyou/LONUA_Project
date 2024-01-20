package com.example.lonua.Seller.model.entity;


import com.example.lonua.Seller.model.request.PatchUpdateSellerReq;
import com.example.lonua.brand.model.entity.Brand;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seller implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellerIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Brand_idx")
    private Brand brand;

    @Column(nullable = false, length = 45, unique = true)
    private String sellerEmail;

    @Column(nullable = false, length = 200)
    private String sellerPassword;

    @Column(nullable = false)
    private String authority;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

    @Column(nullable = false)
    private Boolean status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> authority);
    }

    @Override
    public String getPassword() {
        return sellerPassword;
    }

    @Override
    public String getUsername() {
        return sellerEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void update(PatchUpdateSellerReq patchUpdateSellerReq) {
        if (patchUpdateSellerReq.getSellerPassword() != null) {
            this.sellerPassword = patchUpdateSellerReq.getSellerPassword();
        }
    }
}
