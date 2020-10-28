package com.unpla.model.service;

import com.unpla.entity.document.WasteItem;
import com.unpla.entity.enums.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class WasteAddToWasteItemRequest {
    private List<String> photo;

    private MainWasteCategory mainWasteCategory;

    private SubWasteCategory subWasteCategory;

    private float weightValue;

    private Magnitude magnitude;

    private String userId;


    // TODO : punya waste transaction
    private Date pickUpDate;
    private PeriodOfTime pickUpPeriod;
    private long totalPrice;
    private TransactionStatus status;
//    private WasteItem wasteItem;
    private String recyclerId;
}
