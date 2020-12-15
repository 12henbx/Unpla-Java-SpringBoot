package com.unpla.model.controller;


import com.unpla.entity.document.WasteItem;
import com.unpla.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WasteGetToWasteItemResponse {
//     Waste Item

    private List<String> photo;

    private MainWasteCategory mainWasteCategory;

    private SubWasteCategory subWasteCategory;

    private Magnitude magnitude;

    private float weightValue;

    // Waste Transaction

    private String wasteTransactionId;

    private Date pickUpDate;

    private PeriodOfTime pickUpPeriod;

    private long totalPrice;

    private TransactionStatus status;

    private String recyclerName;

    private Long lastModifiedDate;

    private String lastModifiedBy;

    private Long createdDate;

    private String createdBy;
}
