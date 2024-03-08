package com.riko.booking.client.dto;

import com.riko.booking.client.dto.enums.ShowTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Show {
    private Long id;
    private Long theatreId;
    private Long movieId;
    private LocalDate showDate;
    private ShowTime showTime;
}
