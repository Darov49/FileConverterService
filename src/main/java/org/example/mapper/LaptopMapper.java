package org.example.mapper;

import org.example.bean.BrandJson;
import org.example.bean.LaptopJson;
import org.example.bean.LaptopXml;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface LaptopMapper {
    LaptopMapper INSTANCE = Mappers.getMapper(LaptopMapper.class);

    @Mapping(target = "brand", source = "brandJson.name")
    LaptopXml toLaptopXml(LaptopJson laptopJson, BrandJson brandJson);

    LaptopJson toLaptopJson(LaptopXml laptopXml);

}
