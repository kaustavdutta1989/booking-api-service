package com.riko.booking.client.service.implementation;

import com.riko.booking.client.AllocatedSeatClient;
import com.riko.booking.client.dto.AllocatedSeat;
import com.riko.booking.client.service.AllocatedSeatClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AllocatedSeatClientServiceImpl implements AllocatedSeatClientService {

    private final AllocatedSeatClient allocatedSeatClient;

    @Override
    public List<AllocatedSeat> fetchAllocatedSeats(List<Long> allocateIds) {
        try {
            List<AllocatedSeat> allocatedSeats = allocatedSeatClient.fetchAllocatedSeats(allocateIds).getBody();
            log.info("found allocated seats: {} for the ids: [{}]", allocatedSeats, allocateIds);
            return allocatedSeats;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("allocated seats not found with ids: " + allocateIds);
        }
    }

    @Override
    public List<AllocatedSeat> allocateSeats(List<AllocatedSeat> allocatedSeats) {
        try {
            List<AllocatedSeat> seats = allocatedSeatClient.allocateSeats(allocatedSeats).getBody();
            log.info("created allocated seats: {}", seats);
            return seats;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("allocated seats not created for : " + allocatedSeats);
        }
    }

    @Override
    public void deallocateSeats(@RequestParam Long ticketId) {
        try {
            allocatedSeatClient.deallocateSeats(ticketId);
            log.info("deallocated seats for ticket id: {}", ticketId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("deallocate not completed with ticket id: " + ticketId);
        }
    }
}
