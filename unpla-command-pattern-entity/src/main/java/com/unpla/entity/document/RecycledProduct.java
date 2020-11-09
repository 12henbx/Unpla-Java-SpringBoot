package com.unpla.entity.document;

import com.unpla.entity.embedded.Review;
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
public class RecycledProduct extends BaseClass {
    @Id
    private String id;
    private List<String> productImages;
    private String name;
    private long price;
    private int quantity;
    private float totalRating;
    private List<Review> reviews;
    private long purchasedTimes;
    private String recyclerId;

//    @LastModifiedDate
//    private Long lastModifiedDate;
//
//    @LastModifiedBy
//    private String lastModifiedBy;
//
//    @CreatedDate
//    private Long createdDate;
//
//    @CreatedBy
//    private String createdBy;
}
