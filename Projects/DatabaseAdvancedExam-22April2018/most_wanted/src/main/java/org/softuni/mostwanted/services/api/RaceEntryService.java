package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.binding.xml.RaceEntryXMLImportDTO;
import org.softuni.mostwanted.domain.models.RaceEntry;

public interface RaceEntryService {


    void create(RaceEntryXMLImportDTO dto);

    Integer getLastEnteredId();

    RaceEntry getById(Integer id);
}
