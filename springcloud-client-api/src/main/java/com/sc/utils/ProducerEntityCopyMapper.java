package com.sc.utils;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProducerEntityCopyMapper {
    ProducerEntityCopyMapper INSTANCE = Mappers.getMapper(ProducerEntityCopyMapper.class);

}
