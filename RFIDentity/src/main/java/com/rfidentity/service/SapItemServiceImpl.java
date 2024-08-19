package com.rfidentity.service;

import com.rfidentity.RFIDentity.api.dto.InventoryDTO;
import com.rfidentity.RFIDentity.api.dto.SapItemDTO;
import com.rfidentity.RFIDentity.model.SapItem;
import com.rfidentity.RFIDentity.repo.SapItemRepo;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SapItemServiceImpl implements SapItemService {

    @Autowired
    private SapItemRepo sapItemRepo;

    @Override
    public List<SapItemDTO> getAllSapItem() {
        return StreamSupport.stream(sapItemRepo.findAll().spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SapItemDTO convertToDTO(SapItem sapItem) {
        SapItemDTO dto = new SapItemDTO();
        dto.setId(sapItem.getId());
        dto.setAssetNo(sapItem.getAssetNo());
        dto.setSubNo(sapItem.getSubNo());
        dto.setCapitalizedDate(sapItem.getCapitalizedDate());
        dto.setDescription(sapItem.getDescription());
        dto.setRoom(sapItem.getRoom());
        dto.setAssetId(sapItem.getAssetId());

        dto.setInventoryId(sapItem.getInventoryId().getId());

        return dto;
    }

    //excel file
    private static final Logger logger = (Logger) LoggerFactory.getLogger(SapItemServiceImpl.class);
    public List<SapItemDTO> getSapItemByAssetId() {
        List<SapItemDTO> sapItemDTOS = new ArrayList<>();
        Workbook workbook = null;

        try{
            workbook = WorkbookFactory.create(new File("./SAP_20240414.xlsx"));
            logger.info("Number of sheets in workbook: " + workbook.getNumberOfSheets());

            workbook.forEach(sheet -> {
                logger.info("Sheet: " + sheet.getSheetName());

                DataFormatter dataFormatter = new DataFormatter();
                int index = 0;
                for (Row row : sheet) {
                    if(index++ == 0) continue;
                    SapItem sapItem = new SapItem();
                    if(row.getCell(0) != null && row.getCell(0).getCellType())


                }
            })

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
