package org.softuni.ruk.services.api;

import org.softuni.ruk.domain.dto.binding.xml.CardXMLImportDTO;

public interface CardService {
    void create(CardXMLImportDTO dto);
}
