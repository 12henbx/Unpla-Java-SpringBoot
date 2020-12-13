package com.unpla.model.controller;

import com.unpla.entity.embedded.Coordinate;
import com.unpla.entity.enums.MainWasteCategory;
import com.unpla.entity.enums.SubWasteCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecyclerAddResponse { // Todo: nambah recycler buat set list of recycler
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

    private Long lastModifiedDate;

    private String lastModifiedBy;

    private Long createdDate;

    private String createdBy;
}
