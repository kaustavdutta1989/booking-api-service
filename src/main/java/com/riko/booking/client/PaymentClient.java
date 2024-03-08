package com.riko.booking.client;

import com.riko.booking.client.dto.Payment;
import com.riko.booking.client.dto.PaymentMinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "${feign.client.payment.name}",
        url = "${feign.client.payment.url}",
        path = "${feign.client.payment.path}"
)
public interface PaymentClient {

    @PostMapping
    ResponseEntity<Payment> createPayment(PaymentMinDTO paymentMinDTO);

    @GetMapping
    ResponseEntity<Payment> getPayment(@RequestParam Long id);
}
