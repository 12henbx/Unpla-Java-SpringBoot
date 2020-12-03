package com.unpla.model.controller;

import com.unpla.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WasteGetListByUsernameResponse { //TODO: terakhir disini

    private Long total;

    private List<WasteGetToWasteItemResponse> ListWasteItem;
}
