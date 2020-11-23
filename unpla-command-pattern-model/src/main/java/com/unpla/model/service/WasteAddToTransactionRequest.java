package com.unpla.model.service;

import com.unpla.entity.document.WasteItem;
import com.unpla.entity.enums.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class WasteAddToTransactionRequest { // TODO: ini dicek lagi alurnya dan isinya

    private Date pickUpDate;

    private PeriodOfTime pickUpPeriod;

    private long totalPrice;

    private TransactionStatus status;

    private String wasteItemId;

    private String recyclerId;

}
