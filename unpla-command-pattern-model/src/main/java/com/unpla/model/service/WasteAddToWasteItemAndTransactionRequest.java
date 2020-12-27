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
    @NotBlank
    private List<String> photo;

    @NotBlank
    private MainWasteCategory mainWasteCategory;

    private SubWasteCategory subWasteCategory;

    @NotBlank
    private float weightValue;

    @NotBlank
    private Magnitude magnitude;

    @NotBlank
    private String userId;

    @NotBlank
    private Boolean isDelete;

    //WasteTransaction

    @NotBlank
    private Date pickUpDate;

    @NotBlank
    private PeriodOfTime pickUpPeriod;

    @NotBlank
    private long totalPrice;

    @NotBlank
    private TransactionStatus status;

    @NotBlank
    private String desc;

    @NotBlank
    private String recyclerId;

    @NotBlank
    private String wasteItemId;
}
