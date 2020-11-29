package com.unpla.model.controller;

import com.unpla.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WasteGetFluxByUsernameResponse {
    // Waste Item

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

    @LastModifiedDate
    private Long lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    private Long createdDate;

    @CreatedBy
    private String createdBy;
}
