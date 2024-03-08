package com.riko.booking.client.dto.composite;

import com.riko.booking.client.dto.enums.SeatType;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class TheatreSeatTypeId implements Serializable {
    private Long theatreId;
    private SeatType seatType;
    private String hallName;
}
