package com.riko.booking.client.service;

import com.riko.booking.client.dto.Seat;
import com.riko.booking.client.dto.enums.SeatType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SeatClientService {
    Seat getSeatSetupByTheatreIdAndSeatTypeAndHallName(Long theatreId, SeatType seatType, String hallLikeSearchKey);
}
