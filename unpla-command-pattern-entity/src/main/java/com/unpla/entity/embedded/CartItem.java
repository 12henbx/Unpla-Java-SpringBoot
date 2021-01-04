package com.unpla.entity.embedded;

import com.unpla.entity.document.RecycledProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private int quantity;

    private String recycledProductId;
}
