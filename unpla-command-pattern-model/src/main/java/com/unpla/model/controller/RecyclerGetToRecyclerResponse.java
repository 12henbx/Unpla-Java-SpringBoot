package com.unpla.model.controller;

import com.unpla.entity.embedded.Coordinate;
import com.unpla.entity.embedded.MainWastePrice;
import com.unpla.entity.embedded.SubWastePrice;
import com.unpla.entity.enums.MainWasteCategory;
import com.unpla.entity.enums.SubWasteCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecyclerGetToRecyclerResponse {
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
}
