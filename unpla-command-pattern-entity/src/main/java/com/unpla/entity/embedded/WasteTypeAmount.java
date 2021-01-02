package com.unpla.entity.embedded;

import com.unpla.entity.enums.SubWasteCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WasteTypeAmount {

    private SubWasteCategory wasteCategory;

    private String photoPath;

    private int quantity;
}
