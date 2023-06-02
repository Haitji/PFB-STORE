package com.kreitek.store.application.service.service;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.domain.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<ItemDTO> getAllItems();
    List<ItemDTO> getAllItemsByCategory(Long id);
    Optional<ItemDTO> getItemById(Long id);
    ItemDTO saveItem(ItemDTO item);
    void deleteItem(Long id);


    Page<ItemDTO> getItemByCriteriaStringPaged(Pageable pageable, String filter);
}
