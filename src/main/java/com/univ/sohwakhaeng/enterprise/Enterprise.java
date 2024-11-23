package com.univ.sohwakhaeng.enterprise;

import com.univ.sohwakhaeng.cart.Cart;
import com.univ.sohwakhaeng.enterprise.api.dto.EnterpriseRequestDto;
import com.univ.sohwakhaeng.enterprise.repository.EnterpriseRepository;
import com.univ.sohwakhaeng.product.Product;
import com.univ.sohwakhaeng.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.util.List;
import kotlin.BuilderInference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enterprise_id")
    private Long id;

    @Column(name = "enterprise_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String description;

    private Double star;

    private Double latitude;

    private Double longitude;

    private String address;

    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    public static Enterprise createEnterprise(EnterpriseRequestDto enterpriseRequestDto) {
        return Enterprise.builder()
                .id(enterpriseRequestDto.enterpriseId())
                .name(enterpriseRequestDto.enterpriseName())
                .category(Category.valueOf(enterpriseRequestDto.category()))
                .description(enterpriseRequestDto.description())
                .star(enterpriseRequestDto.star())
                .latitude(enterpriseRequestDto.latitude())
                .longitude(enterpriseRequestDto.longitude())
                .address(enterpriseRequestDto.address())
                .build();
    }

}
