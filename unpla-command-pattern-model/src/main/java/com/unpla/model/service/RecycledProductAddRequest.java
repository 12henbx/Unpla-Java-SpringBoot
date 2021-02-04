package com.unpla.model.service;

import com.unpla.entity.embedded.Review;
import com.unpla.entity.embedded.WasteTypeAmount;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.*;

import java.util.List;

@Data
@Builder
public class RecycledProductAddRequest implements ServiceRequest{

//    private String id;

    private List<String> productImages;

    private String name;

    private long price;

    private int quantity;

    private float totalRating;

    private List<Review> reviews;

    private long purchasedTimes;

    private List<WasteTypeAmount> orderedWasteList;

    private List<WasteTypeAmount> materialList;

    private String recyclerId;

    private boolean isDelete;
}
