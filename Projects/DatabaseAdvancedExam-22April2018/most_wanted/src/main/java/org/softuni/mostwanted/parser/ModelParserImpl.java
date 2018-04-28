package org.softuni.mostwanted.parser;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.springframework.stereotype.Component;

@Component
public final class ModelParserImpl implements ModelParser {

    public ModelParserImpl() {

    }
    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, destinationClass);
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);
        return modelMapper.map(source, destinationClass);
    }
}
