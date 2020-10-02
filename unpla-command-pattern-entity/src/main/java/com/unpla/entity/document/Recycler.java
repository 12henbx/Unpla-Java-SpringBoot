package com.unpla.entity.document;

import com.unpla.entity.embedded.Coordinate;
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
public class Recycler {
    @Id
    private String id;
    private String profilePhoto;
    private String headerPhoto;
    private String name;
    private String address;
    private Coordinate coordinate;
    private String description;
    private List<String> recycledProductId;
    private List<MainWasteCategory> mainWasteCategories;
    private List<SubWasteCategory> subWasteCategories;

    private Date creationDate = new Date();
    private String createdBy;
    private String lastUpdateBy;
    private Date lastUpdateDate;
}
