package com.univ.sohwakhaeng.product;

import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.product.api.dto.ProductRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Column(name = "product_name")
    private String name;

    private String unit;

    @Column(name = "product_price")
    private Integer price;

    public static Product createProduct(ProductRequestDto productRequestDto, Enterprise enterprise) {
        return Product.builder()
                .id(productRequestDto.id())
                .enterprise(enterprise)
                .name(productRequestDto.name())
                .unit(productRequestDto.unit())
                .price(productRequestDto.price())
                .build();
    }

}
