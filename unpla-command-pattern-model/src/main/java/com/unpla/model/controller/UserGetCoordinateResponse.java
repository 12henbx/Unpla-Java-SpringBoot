package com.unpla.model.controller;

import com.unpla.entity.embedded.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGetCoordinateResponse {

    private String userId;

    private Coordinate coordinate;

    private String address;
}
