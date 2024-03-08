package com.riko.booking.client;

import com.riko.booking.client.dto.Refund;
import com.riko.booking.client.dto.RefundMinDTO;
import com.riko.booking.client.dto.enums.TransactionalStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "${feign.client.refund.name}",
        url = "${feign.client.refund.url}",
        path = "${feign.client.refund.path}"
)
public interface RefundClient {

    @GetMapping
    ResponseEntity<Refund> getRefund(@RequestParam Long id);

    @PostMapping
    ResponseEntity<Refund> createRefund(@RequestBody RefundMinDTO refundMinDTO);

    @PatchMapping
    ResponseEntity<Refund> statusChangeRefund(@RequestParam Long id,
                                              @RequestParam TransactionalStatus status);

    @GetMapping("/ticket")
    ResponseEntity<Refund> getRefundByTicketId(@RequestParam Long ticketId);
}
