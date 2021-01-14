package com.unpla.model.service;

import com.unpla.entity.enums.SubWasteCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecyclerGetListBySubWasteRequest implements ServiceRequest{

    private SubWasteCategory subWasteCategory;

    //    @NotBlank
//    private int page;

    //    @NotBlank
//    private int size;
}
