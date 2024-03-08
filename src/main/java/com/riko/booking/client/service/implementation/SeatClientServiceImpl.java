package com.riko.booking.client.service.implementation;

import com.riko.booking.client.SeatClient;
import com.riko.booking.client.dto.Seat;
import com.riko.booking.client.dto.enums.SeatType;
import com.riko.booking.client.service.SeatClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeatClientServiceImpl implements SeatClientService {

    private final SeatClient seatClient;

    public Seat getSeatSetupByTheatreIdAndSeatTypeAndHallName(Long theatreId, SeatType seatType, String hallName) {
        try {
            Seat seat = seatClient.getSeatSetupByTheatreIdAndSeatTypeAndHallName(theatreId, seatType, hallName).getBody();
            log.info("seat found: {}", seat);
            return seat;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("seat not found with"
                    + " theatreId: " + theatreId
                    + " seatType: " + seatType
                    + " hallName: " + hallName);
        }
    }
}
