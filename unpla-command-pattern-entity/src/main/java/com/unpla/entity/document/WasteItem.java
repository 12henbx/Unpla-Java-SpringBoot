package com.unpla.entity.document;

import com.unpla.entity.enums.PeriodOfTime;
import com.unpla.entity.enums.WasteCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class WasteItem {
    @Id
    private String id;
    private List<String> photo;
    private WasteCategory category;
    private float kilogramWeight;
    private User user;
    private Date pickUpDate;
    private PeriodOfTime period;

    private Date creationDate = new Date();
    private String createdBy;
    private String lastUpdateBy;
    private Date lastUpdateDate;
}
