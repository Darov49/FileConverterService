package ru.vyatsu.fileconverter.mapper;

import ru.vyatsu.fileconverter.bean.BrandJson;
import ru.vyatsu.fileconverter.bean.LaptopJson;
import ru.vyatsu.fileconverter.bean.LaptopXml;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface LaptopMapper {
    LaptopMapper INSTANCE = Mappers.getMapper(LaptopMapper.class);

    @Mapping(target = "brand", source = "brandJson.name")
    LaptopXml toLaptopXml(final LaptopJson laptopJson, final BrandJson brandJson);

    LaptopJson toLaptopJson(final LaptopXml laptopXml);

}
