package com.riko.booking.client.service.implementation;

import com.riko.booking.client.dto.User;
import com.riko.booking.client.ViewerClient;
import com.riko.booking.client.service.UserClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserClientServiceImpl implements UserClientService {

    private final ViewerClient viewerClient;

    public User getViewerById(Long id) {
        try {
            User user = viewerClient.getViewerById(id).getBody();
            log.info("user found : {}", user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("user not found with id: " + id);
        }
    }
}
