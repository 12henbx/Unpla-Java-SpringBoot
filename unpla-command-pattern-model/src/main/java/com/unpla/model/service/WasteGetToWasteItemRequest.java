package com.unpla.model.service;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class WasteGetToWasteItemRequest {
    @NotBlank
    private String wasteItemId;
}
