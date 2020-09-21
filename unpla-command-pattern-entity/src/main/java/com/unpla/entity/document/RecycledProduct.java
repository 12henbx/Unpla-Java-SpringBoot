package com.unpla.entity.document;

import com.unpla.entity.embedded.Review;
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
public class RecycledProduct {
    @Id
    private String id;
    private List<String> productImages;
    private String name;
    private long price;
    private int quantity;
    private float totalRating;
    private List<Review> reviews;
    private String idUserRecycler;

    private Date creationDate = new Date();
    private String createdBy;
    private String lastUpdateBy;
    private Date lastUpdateDate;
}
