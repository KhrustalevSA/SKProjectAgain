package com.simplekitchen.project.business.mapper.common;


import com.simplekitchen.project.dto.common.LongListImpl;
import com.simplekitchen.project.dto.common.api.LongList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * интерфейс маппера для общих объектов
 * @since 02.03.2023
 * @author KhrustalevSA
 */
@Mapper
public interface CommonMapper {

    /**
     * переменная - экземпляр класса
     */
    CommonMapper INSTANCE = Mappers.getMapper(CommonMapper.class);

    /**
     * преобразование ДАО списка целых чисел к ДТО списку
     * @param longList ДАО список чисел
     * @return ДТО список чисел
     */
    LongListImpl map(com.simplekitchen.project.dao.entity.common.entity.api.LongList longList);

    /**
     * преобразование ДТО списка целых чисел к ДАО списку
     * @param longList ДТО список чисел
     * @return ДАО список чисел
     */
    com.simplekitchen.project.dao.entity.common.entity.LongListImpl map(LongList longList);
}
