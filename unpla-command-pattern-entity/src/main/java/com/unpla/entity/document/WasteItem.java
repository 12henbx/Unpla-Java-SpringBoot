package com.unpla.entity.document;

import com.unpla.entity.enums.MainWasteCategory;
import com.unpla.entity.enums.SubWasteCategory;
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
    private MainWasteCategory mainWasteCategory;
    private SubWasteCategory subWasteCategory;
    private float kilogramWeight;
    private String userId;

    private Date creationDate = new Date();
    private String createdBy;
    private String lastUpdateBy;
    private Date lastUpdateDate;
}
