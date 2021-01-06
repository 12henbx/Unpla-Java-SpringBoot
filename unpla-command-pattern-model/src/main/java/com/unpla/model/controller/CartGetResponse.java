package com.unpla.model.controller;

import com.unpla.entity.embedded.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartGetResponse {

    private String id;

    private List<CartItem> cartItemList;

    private Long lastModifiedDate;

    private String lastModifiedBy;

    private Long createdDate;

    private String createdBy;
}
