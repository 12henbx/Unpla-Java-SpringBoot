package com.unpla.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    private boolean isActive;
    private String profilePhoto;
    private String headerPhoto;
    private String name;
    private String address;
    private Coordinate coordinate;
    private String description;

    private Date creationDate = new Date();
    private String createdBy;
    private String lastUpdateBy;
    private Date lastUpdateDate;
}
