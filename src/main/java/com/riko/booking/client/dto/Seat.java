package com.riko.booking.client.dto;

import com.riko.booking.client.dto.composite.TheatreSeatTypeId;
import jakarta.persistence.EmbeddedId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {
    @EmbeddedId
    private TheatreSeatTypeId theatreSeatType;
    private Integer rowCount;
    private Integer seatCount;
    private Integer seatCost;
}
