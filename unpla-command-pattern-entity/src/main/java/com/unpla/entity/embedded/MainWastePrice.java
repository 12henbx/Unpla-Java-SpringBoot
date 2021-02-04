package com.unpla.entity.embedded;

import com.unpla.entity.enums.Magnitude;
import com.unpla.entity.enums.MainWasteCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainWastePrice {

    private MainWasteCategory mainWasteCategory;

    private int price;

    private Magnitude magnitude;
}
