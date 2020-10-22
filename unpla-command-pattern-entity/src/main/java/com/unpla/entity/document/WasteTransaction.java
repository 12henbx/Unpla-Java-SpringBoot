package com.unpla.entity.document;

import com.unpla.entity.enums.PeriodOfTime;
import com.unpla.entity.enums.TransactionStatus;
import org.springframework.data.annotation.*;

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

    @LastModifiedDate
    private Long lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    private Long createdDate;

    @CreatedBy
    private String createdBy;
}
