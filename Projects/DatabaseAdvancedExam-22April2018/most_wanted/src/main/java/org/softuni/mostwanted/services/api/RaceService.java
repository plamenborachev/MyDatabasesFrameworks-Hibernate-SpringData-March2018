package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.binding.xml.RaceXMLImportDTO;

public interface RaceService {

    void create(RaceXMLImportDTO dto);

    Integer getLastId();

}
