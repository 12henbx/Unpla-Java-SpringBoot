package com.unpla.entity.document;

import com.unpla.entity.embedded.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User{
    @Id
    private String id;
    private String fullName;
    private String username;
    private String password;
    private String profilePic;
    private String phone;
    private int balance;
    private int point;
    private String Address;
    private Coordinate coordinate;
    private String recyclerId;
    private List<String> cartId;
    private boolean isRecyclerActive;


    private Date creationDate = new Date();
    private String createdBy;
    private String lastUpdateBy;
    private Date lastUpdateDate;

}
