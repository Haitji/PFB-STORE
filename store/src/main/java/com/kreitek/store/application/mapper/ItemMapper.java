package com.kreitek.store.application.mapper;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.dto.ItemSimpleDTO;
import com.kreitek.store.domain.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {CategoryMapper.class, UserMapper.class})
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

    default Item fromId(Long id){
        if(id == null) return null;
        Item item = new Item();
        item.setId(id);
        return item;
    }
    @Override
    @Mapping(source = "category.id",target = "categoryId")
    @Mapping(source = "category.name",target = "categoryName")
    ItemDTO toDto(Item entity);


    @Override
    @Mapping(source = "categoryId",target = "category")
    Item toEntity(ItemDTO dto);

}