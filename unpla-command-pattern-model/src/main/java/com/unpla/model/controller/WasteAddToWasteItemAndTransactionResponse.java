package com.unpla.model.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.unpla.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WasteAddToWasteItemAndTransactionResponse {
    private Boolean isSuccess;

    private List<String> photoListPath;

    private MainWasteCategory mainWasteCategory;

    private SubWasteCategory subWasteCategory;

    private float weightValue;

    private Magnitude magnitude;

    private String userId;

    //WasteTransaction

    private Date pickUpDate;

    private PeriodOfTime pickUpPeriod;

    private long totalPrice;

    private TransactionStatus status;

    private String desc;

    private String recyclerId;
}
