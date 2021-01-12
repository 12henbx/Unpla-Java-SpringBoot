package com.unpla.model.controller;

import com.unpla.entity.enums.NotificationGroup;
import com.unpla.entity.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationGetToNotificationResponse {

    private String id;

    private String idRequester;

    private String idReceiver;

    private NotificationGroup nGroupForReceiver; // pembeli sampah, penjual sampah, pembeli barang, penjual barang

    private NotificationType notificationType; // pesanan_baru, diproses, dikirim, sampai

    private String itemId;

    private boolean isRead;

    private boolean isDelete;

    private Long lastModifiedDate;

    private String lastModifiedBy;

    private Long createdDate;

    private String createdBy;
}
