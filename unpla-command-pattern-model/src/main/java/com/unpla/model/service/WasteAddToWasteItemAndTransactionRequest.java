package com.unpla.model.service;

import com.unpla.entity.document.WasteItem;
import com.unpla.entity.enums.*;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class WasteAddToWasteItemAndTransactionRequest implements ServiceRequest {
//    @NotBlank
    private List<String> photos;

    private MainWasteCategory mainWasteCategory;

    private SubWasteCategory subWasteCategory;

    private float weightValue;

    private Magnitude magnitude;

    @NotBlank
    private String userId;

    private Boolean isDelete;

    //WasteTransaction

    private Date pickUpDate;

    private PeriodOfTime pickUpPeriod;

    private long totalPrice;

    private TransactionStatus status;

    private String desc;

    @NotBlank
    private String recyclerId;
}
