package com.unpla.entity.document;

import com.unpla.entity.enums.NotificationGroup;
import com.unpla.entity.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Notification {
    @Id
    private String id;

    private String idSender;

    private String idReceiver;

    private NotificationGroup norificationGroup; // pembeli sampah, penjual sampah, pembeli barang, penjual barang

    private NotificationType notificationType; // pesanan_baru, diproses, dikirim, sampai

    private String content;

    private boolean isRead;

    private boolean isDelete;

    @LastModifiedDate
    private Long lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    private Long createdDate;

    @CreatedBy
    private String createdBy;
}
