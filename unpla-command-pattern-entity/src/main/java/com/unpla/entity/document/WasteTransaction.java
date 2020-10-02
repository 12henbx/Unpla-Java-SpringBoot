package com.unpla.entity.document;

import com.unpla.entity.enums.PeriodOfTime;
import com.unpla.entity.enums.TransactionStatus;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class WasteTransaction {
    @Id
    private String id;
    private Date pickUpDate;
    private PeriodOfTime pickUpPeriod;
    private long totalPrice;
    private TransactionStatus status;
    private WasteItem wasteItem;
    private String userId;
    private String recyclerId;
}
