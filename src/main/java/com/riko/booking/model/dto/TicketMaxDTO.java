package com.riko.booking.model.dto;

import com.riko.booking.client.dto.SeatSpot;
import com.riko.booking.client.dto.composite.TheatreSeatTypeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketMaxDTO {
    private Long showId;
    private Long userId;
    private Integer ticketCount;
    @EmbeddedId
    private TheatreSeatTypeId theatreHallSeat;
    private List<SeatSpot> seatSpots;
}
