package com.univ.sohwakhaeng.contract.domain;

import com.univ.sohwakhaeng.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContractProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @OneToOne
    @JoinColumn(name = "product_product_id")
    private Product product;

    @Column(name = "product_name")
    private String productName;

    private int quantity;

    @Builder
    public ContractProducts(Contract contract, Product product, String productName, int quantity) {
        this.contract = contract;
        this.product = product;
        this.productName = productName;
        this.quantity = quantity;
    }
}
