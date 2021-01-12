package com.unpla.model.controller;

import com.unpla.entity.embedded.Review;
import com.unpla.entity.embedded.WasteTypeAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecycledProductGetResponse {

    private String id;

    private List<String> productImages;

    private String name;

    private long price;

    private int quantity;

    private float totalRating;

    private int submitRatingCount;

    private List<Review> reviews;

    private long purchasedTimes;

    private List<WasteTypeAmount> orderedWasteList;

    private List<WasteTypeAmount> materialList;

    private String recyclerId;

    private Long lastModifiedDate;

    private String lastModifiedBy;

    private Long createdDate;

    private String createdBy;
}
