package com.unpla.entity.document;

import com.unpla.entity.enums.PeriodOfTime;
import com.unpla.entity.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class WasteTransaction extends BaseClass {
    @Id
    private String id;
    private Date pickUpDate;
    private PeriodOfTime pickUpPeriod;
    private long totalPrice;
    private TransactionStatus status;
    private WasteItem wasteItem;
    private String userId;
    private String recyclerId;

//    @LastModifiedDate
//    private Long lastModifiedDate;
//
//    @LastModifiedBy
//    private String lastModifiedBy;
//
//    @CreatedDate
//    private Long createdDate;
//
//    @CreatedBy
//    private String createdBy;
}
