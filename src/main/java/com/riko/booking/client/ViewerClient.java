package com.riko.booking.client;

import com.riko.booking.client.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
        name = "${feign.client.viewer.name}",
        url = "${feign.client.viewer.url}",
        path = "${feign.client.viewer.path}"
)
public interface ViewerClient {

    @GetMapping("/{id}")
    ResponseEntity<User> getViewerById(@PathVariable Long id);
}
