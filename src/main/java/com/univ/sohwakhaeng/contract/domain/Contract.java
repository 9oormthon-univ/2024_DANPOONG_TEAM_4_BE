package com.univ.sohwakhaeng.contract.domain;

import com.univ.sohwakhaeng.enterprise.Enterprise;
import com.univ.sohwakhaeng.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long id;

    @Column(name = "delivery_week")
    private int deliveryWeek;

    @Column(name = "delivery_day")
    private String deliveryDay;

    @Column(name = "take_method")
    @Enumerated(EnumType.STRING)
    private MethodToTake takeMethod;

    @Column(name = "requested_term")
    private String requestedTerm;

    @Column(name = "total_price")
    private long totalPrice;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractProducts> contractProducts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Builder
    public Contract(int deliveryWeek, String deliveryDay, MethodToTake takeMethod, String requestedTerm, long totalPrice, boolean status, User user, Enterprise enterprise) {
        this.deliveryWeek = deliveryWeek;
        this.deliveryDay = deliveryDay;
        this.takeMethod = takeMethod;
        this.requestedTerm = requestedTerm;
        this.totalPrice = totalPrice;
        this.user = user;
        this.enterprise = enterprise;
    }
}
