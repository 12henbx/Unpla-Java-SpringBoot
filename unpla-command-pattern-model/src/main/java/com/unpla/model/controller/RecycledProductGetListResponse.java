package com.unpla.model.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecycledProductGetListResponse {

    private int total;

    private List<RecycledProductGetResponse> recycledProductList;
}
