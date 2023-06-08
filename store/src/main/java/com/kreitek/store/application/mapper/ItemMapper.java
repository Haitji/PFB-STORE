package com.kreitek.store.application.mapper;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.ItemSimpleDTO;
import com.kreitek.store.domain.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CategoryMapper.class, UserMapper.class})
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

    @Override
    @Mapping(source = "category.id",target = "categoryId")
    @Mapping(source = "category.name",target = "categoryName")
    ItemDTO toDto(Item entity);


    @Override
    @Mapping(source = "categoryId",target = "category")
    @Mapping(target = "usuarios",ignore = true)
    Item toEntity(ItemDTO dto);

    @Override
    @Mapping(target = "usuarios",ignore = true)
    List<Item> toEntity(List<ItemDTO> dto);

}
