package com.riko.booking.client.service;

import com.riko.booking.client.dto.AllocatedSeat;

import java.util.List;

public interface AllocatedSeatClientService {

    List<AllocatedSeat> fetchAllocatedSeats(List<Long> deallocateIds);

    List<AllocatedSeat> allocateSeats(List<AllocatedSeat> allocatedSeats);

    void deallocateSeats(Long ticketId);
}
