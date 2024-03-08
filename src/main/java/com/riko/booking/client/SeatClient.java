package com.riko.booking.client;

import com.riko.booking.client.dto.Seat;
import com.riko.booking.client.dto.enums.SeatType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "${feign.client.seat.name}",
        url = "${feign.client.seat.url}",
        path = "${feign.client.seat.path}"
)
public interface SeatClient {

    @GetMapping("/theatre/{theatreId}/seat-type/{seatType}/hall")
    ResponseEntity<Seat> getSeatSetupByTheatreIdAndSeatTypeAndHallName(@PathVariable Long theatreId,
                                                                                   @PathVariable SeatType seatType,
                                                                                   @RequestParam String hallName);
}
