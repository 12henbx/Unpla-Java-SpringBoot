package com.unpla.model.controller;

import com.unpla.entity.embedded.Coordinate;
import com.unpla.entity.embedded.MainWastePrice;
import com.unpla.entity.embedded.SubWastePrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecyclerAddResponse {
    private String id;

    private String profilePhoto;

    private String headerPhoto;

    private String name;

    private String address;

    private Coordinate coordinate;

    private String description;

    private List<String> recycledProductId;

    private List<MainWastePrice> mainWastePriceList;

    private List<SubWastePrice> subWastePriceList;

    private Long lastModifiedDate;

    private String lastModifiedBy;

    private Long createdDate;

    private String createdBy;
}
