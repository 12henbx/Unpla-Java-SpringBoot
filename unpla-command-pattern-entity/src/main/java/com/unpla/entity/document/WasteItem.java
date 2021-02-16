package com.unpla.entity.document;

import com.unpla.entity.enums.Magnitude;
import com.unpla.entity.enums.MainWasteCategory;
import com.unpla.entity.enums.SubWasteCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
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

    private List<String> photoListPath;

    private MainWasteCategory mainWasteCategory;

    private SubWasteCategory subWasteCategory;

    private Magnitude magnitude;

    private float weightValue;

    private String userId;

    private String wasteTransactionId;

    private Boolean isDelete;

    @LastModifiedDate
    private Long lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    private Long createdDate;

    @CreatedBy
    private String createdBy;
}
