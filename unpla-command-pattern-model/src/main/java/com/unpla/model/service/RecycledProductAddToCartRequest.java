package com.unpla.model.service;

import com.unpla.entity.embedded.CartItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecycledProductAddToCartRequest implements ServiceRequest{

    private String id;

    private List<CartItem> cartItemList;

    private boolean isDelete;
}
