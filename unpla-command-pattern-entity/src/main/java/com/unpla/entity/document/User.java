package com.unpla.entity.document;

import com.unpla.entity.embedded.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    @Id
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

    private boolean isRecyclerActive;

    private boolean isDelete;

    @LastModifiedDate
    private Long lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    private Long createdDate;

    @CreatedBy
    private String createdBy;

}
