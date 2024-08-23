package com.rfidentity.service;

import com.rfidentity.api.dto.MobileRoomsDTO;

import java.util.List;

public interface MobileService {
    List<MobileRoomsDTO> getRooms();
}
