package com.kreitek.store.application.mapper;

import com.kreitek.store.application.dto.OrderDTO;
import com.kreitek.store.application.dto.UserDTO;
import com.kreitek.store.domain.entity.Order;
import com.kreitek.store.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ShoppingCartMapper.class,UserMapper.class,ItemMapper.class})
public interface OrderMapper extends EntityMapper<OrderDTO,Order> {
    @Override
    @Mapping(target = "carritos",ignore = true)
    @Mapping(target = "user",ignore = true)
    Order toEntity(OrderDTO orderDTO);

    @Override
    @Mapping(target = "carritos",ignore = true)
    @Mapping(target = "user",ignore = true)
    List<Order> toEntity(List<OrderDTO> orderDTO);
}
