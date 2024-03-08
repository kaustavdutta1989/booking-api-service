package com.riko.booking.client.service;

import com.riko.booking.client.dto.User;

public interface UserClientService {
    User getViewerById(Long id);
}
