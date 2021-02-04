package com.unpla.model.controller;

import com.unpla.entity.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductTransactionAddResponse {
    private String id;

    private String idRecyclerSeller;

    private String idUserBuyer;

    private List<String> idrecycledProducts;

    private int quantity;

    private long totalPrice;

    private String userId;

    private String recyclerId;

    private String cartId;

    private PaymentMethod paymentMethodEnum;

    private boolean isDelete;

    private Long lastModifiedDate;

    private String lastModifiedBy;

    private Long createdDate;

    private String createdBy;
}
