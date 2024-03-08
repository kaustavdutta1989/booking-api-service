package com.riko.booking.model;

import com.riko.booking.model.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long showId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long paymentId;

    @Column(nullable = false)
    private Integer ticketCost;

    @Column(nullable = false)
    private Integer ticketCount;

    @Column(nullable = false)
    private Double ticketDiscount;

    @Column(nullable = false)
    private TicketStatus ticketStatus;
}
