package com.kreitek.store.application.mapper;


import com.kreitek.store.application.dto.ItemShoppingCartDTO;
import com.kreitek.store.application.dto.ShoppingCartDTO;
import com.kreitek.store.domain.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses={ItemMapper.class})
public interface ShoppingCartMapper extends EntityMapper<ShoppingCartDTO, ShoppingCart>{

    @Mapping(source = "item.id",target = "id")
    @Mapping(source = "item.name",target = "name")
    @Mapping(source = "item.image",target = "image")
    @Mapping(source = "item.category.name",target = "categoryName")
    @Mapping(source = "item.price",target = "price")
    @Mapping(source = "units",target = "units")
    ItemShoppingCartDTO shoppingCartToItemCartDTO(ShoppingCart shoppingCart);
}
