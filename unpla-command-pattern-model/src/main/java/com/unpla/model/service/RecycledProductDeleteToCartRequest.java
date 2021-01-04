package com.unpla.model.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecycledProductDeleteToCartRequest implements ServiceRequest{
    private String id;
}
