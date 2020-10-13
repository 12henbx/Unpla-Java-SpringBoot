package com.unpla.model.service;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class AddUserToUserResponse {

    @NotBlank
    private String userId;

    @NotBlank
    private String username;

    @NotBlank
    private String nama;
}
