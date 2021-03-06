package com.unpla.model.service;

import com.unpla.entity.embedded.Coordinate;
import com.unpla.entity.embedded.MainWastePrice;
import com.unpla.entity.embedded.SubWastePrice;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
public class RecyclerAddRequest implements ServiceRequest{

    private String profilePhoto;

    private String headerPhoto;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    private Coordinate coordinate;

    private String description;

    private List<String> recycledProductId;

    private List<MainWastePrice> mainWastePriceList;

    private List<SubWastePrice> subWastePriceList;
}
