package com.unpla.model.controller;

import com.unpla.entity.embedded.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGetResponse {
    private String id;

    private String fullName;

    private String email;

    private String username;

    private String password;

    private String profilePic;

    private String phone;

    private int balance;

    private int point;

    private String address;

    private Coordinate coordinate;

    private String recyclerId;

    private List<String> cartId;

    private Long lastModifiedDate;

    private String lastModifiedBy;

    private Long createdDate;

    private String createdBy;
}
