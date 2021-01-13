package com.unpla.model.service;


import com.unpla.entity.embedded.Coordinate;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
public class UserAddRequest implements ServiceRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String email;

    @NotBlank
    private String name;

    private String fullName;

    private String profilePic;

    private String phone;

    private int balance;

    private int point;

    private String address;

    private Coordinate coordinate;

    private String recyclerId;

    private List<String> cartId;

    private boolean isRecyclerActive;

    private boolean isDelete;
}
