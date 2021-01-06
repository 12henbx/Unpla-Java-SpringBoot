package com.unpla.model.service;

import com.unpla.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WasteGetToTransactionRequest implements ServiceRequest {

    @NotBlank
    private String wasteTransactionId;

    //    @NotBlank
    private int page;

    //    @NotBlank
    private int size;

}
