package com.kreitek.store.application.mapper;


import com.kreitek.store.application.dto.ItemShoppingCartDTO;
import com.kreitek.store.application.dto.OrderDTO;
import com.kreitek.store.application.dto.ShoppingCartDTO;
import com.kreitek.store.domain.entity.Order;
import com.kreitek.store.domain.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses={ItemMapper.class,UserMapper.class})
public interface ShoppingCartMapper extends EntityMapper<ShoppingCartDTO, ShoppingCart>{

    @Mapping(source = "item.id",target = "id")
    @Mapping(source = "item.name",target = "name")
    @Mapping(source = "item.image",target = "image")
    @Mapping(source = "item.category.name",target = "categoryName")
    @Mapping(source = "item.price",target = "price")
    @Mapping(source = "units",target = "units")
    @Mapping(source = "id",target = "shoppingCartId")
    ItemShoppingCartDTO shoppingCartToItemCartDTO(ShoppingCart shoppingCart);

    @Mapping(source = "item.id",target = "id")
    @Mapping(source = "item.name",target = "name")
    @Mapping(source = "item.image",target = "image")
    @Mapping(source = "item.category.name",target = "categoryName")
    @Mapping(source = "item.price",target = "price")
    @Mapping(source = "units",target = "units")
    @Mapping(source = "id",target = "shoppingCartId")
    List<ItemShoppingCartDTO> shoppingCartToItemCartDTO(List<ShoppingCart> shoppingCart);

    @Override
    @Mapping(target = "order",ignore = true)
    ShoppingCart toEntity(ShoppingCartDTO shoppingCartDTO);

    @Override
    @Mapping(target = "order",ignore = true)
    List<ShoppingCart> toEntity(List<ShoppingCartDTO> shoppingCartDTOS);
}
