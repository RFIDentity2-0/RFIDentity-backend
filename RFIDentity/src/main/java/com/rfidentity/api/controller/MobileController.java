package com.rfidentity.api.controller;

import com.rfidentity.api.dto.MobileRoomsDTO;
import com.rfidentity.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mobile")
public class MobileController {

    @Autowired
    private MobileService mobileService;

    @GetMapping("/rooms")
    public ResponseEntity<MobileRoomsDTO> getRooms(){

    }

}
