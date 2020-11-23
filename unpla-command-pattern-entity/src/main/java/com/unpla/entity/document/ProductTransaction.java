package com.unpla.entity.document;

import com.unpla.entity.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ProductTransaction {
    @Id
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

    @LastModifiedDate
    private Long lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    private Long createdDate;

    @CreatedBy
    private String createdBy;
}
