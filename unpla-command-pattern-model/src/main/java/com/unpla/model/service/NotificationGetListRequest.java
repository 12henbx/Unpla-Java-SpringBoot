package com.unpla.model.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class NotificationGetListRequest implements ServiceRequest{

    @NotBlank
    private String userId;
}
