package com.riko.booking.client.dto;

import com.riko.booking.client.dto.enums.TransactionalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefundMinDTO {
    private Long paymentId;
    private Double amount;
    private TransactionalStatus status;
}
