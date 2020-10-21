package com.unpla.model.service;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class AddUserToUserRequest implements ServiceRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String nama;
}
