package com.rfidentity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//@Service
//@RequiredArgsConstructor
//public class RoomSummaryServiceImpl implements RoomSummaryService {
//
//    private final RoomSummaryRepository repository;
//
//    public Page<RoomSummaryDTO> getRoomSummary(Pageable pageable) {
//
//        return repository.findAll(pageable).map(this::mapToDTO);
//    }
//
//    private RoomSummaryDTO mapToDTO(RoomSummary roomSummary) {
//        return RoomSummaryDTO.builder()
//                .withRoom(roomSummary.getRoom())
//                .withAssetCount(roomSummary.getAssetCount())
//                .withAssetIds(roomSummary.getAssetIds())
//                .withDescriptions(roomSummary.getDescriptions())
//                .withStatuses(roomSummary.getStatuses())
//                .build();
//    }
//}
