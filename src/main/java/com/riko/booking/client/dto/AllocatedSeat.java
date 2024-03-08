package com.riko.booking.client.dto;

import com.riko.booking.client.dto.enums.SeatType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocatedSeat {
    private Long ticketId;
    private Long showId;
    private Long theatreId;
    private SeatType seatType;
    private String hallName;
    private Integer rowSeat;
    private String columnSeat;
}
