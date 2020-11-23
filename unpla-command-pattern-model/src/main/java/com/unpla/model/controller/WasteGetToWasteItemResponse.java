package com.unpla.model.controller;


import com.unpla.entity.document.WasteItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WasteGetToWasteItemResponse {
    WasteItem wasteItem;
}
