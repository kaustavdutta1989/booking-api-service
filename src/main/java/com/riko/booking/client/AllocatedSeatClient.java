package com.riko.booking.client;

import com.riko.booking.client.dto.AllocatedSeat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "${feign.client.allotment.name}",
        url = "${feign.client.allotment.url}",
        path = "${feign.client.allotment.path}"
)
public interface AllocatedSeatClient {
    @GetMapping("/fetch")
    ResponseEntity<List<AllocatedSeat>> fetchAllocatedSeats(@RequestParam List<Long> deallocateIds);

    @PostMapping("/allocate")
    ResponseEntity<List<AllocatedSeat>> allocateSeats(@RequestBody List<AllocatedSeat> allocatedSeats);

    @DeleteMapping("/deallocate")
    void deallocateSeats(@RequestParam Long ticketId);
}
