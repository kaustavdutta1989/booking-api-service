package com.riko.booking.client.dto;

import com.riko.booking.client.dto.enums.TransactionalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refund {
    private Long id;
    private Payment payment;
    private Double amount;
    private TransactionalStatus status;
}
