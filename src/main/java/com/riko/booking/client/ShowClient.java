package com.riko.booking.client;

import com.riko.booking.client.dto.Show;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
        name = "${feign.client.show.name}",
        url = "${feign.client.show.url}",
        path = "${feign.client.show.path}"
)
public interface ShowClient {

    @GetMapping("/{id}")
    ResponseEntity<Show> getShowSetupById(@PathVariable Long id);
}
