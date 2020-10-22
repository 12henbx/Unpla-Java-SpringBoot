package com.unpla.entity.document;

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
    private List<String> photo;
    private MainWasteCategory mainWasteCategory;
    private SubWasteCategory subWasteCategory;
    private float kilogramWeight;
    private String userId;

    @LastModifiedDate
    private Long lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    private Long createdDate;

    @CreatedBy
    private String createdBy;
}
