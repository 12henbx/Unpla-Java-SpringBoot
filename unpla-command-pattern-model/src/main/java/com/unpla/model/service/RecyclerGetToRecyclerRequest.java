package com.unpla.model.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecyclerGetToRecyclerRequest implements ServiceRequest {
    @NotBlank
    private String recyclerId;
}
