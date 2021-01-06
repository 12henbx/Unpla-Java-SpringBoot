package com.unpla.model.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecycledProductDeleteToCartResponse {

    private String recycledProductId;

    private String cartId;

    private Long lastModifiedDate;

    private String lastModifiedBy;

    private Long createdDate;

    private String createdBy;
}
