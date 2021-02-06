package com.unpla.model.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RProductGetListByRecyclerRequest implements ServiceRequest {

    private String recyclerId;
}
