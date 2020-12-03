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
public class WasteAddToTransactionRequest implements ServiceRequest { 

    @NotBlank
    private Date pickUpDate;

    @NotBlank
    private PeriodOfTime pickUpPeriod;

    @NotBlank
    private long totalPrice;

    @NotBlank
    private String recyclerId;

}
