package com.riko.booking.client;

import com.riko.booking.client.dto.GlobalOffer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "${feign.client.discount.name}",
        url = "${feign.client.discount.url}",
        path = "${feign.client.discount.path}"
)
public interface GlobalOfferClient {

    @GetMapping("/global")
    ResponseEntity<List<GlobalOffer>> getAllGlobalOffers();
}
