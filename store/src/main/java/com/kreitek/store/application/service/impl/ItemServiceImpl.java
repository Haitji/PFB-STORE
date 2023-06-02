package com.kreitek.store.application.service.impl;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.mapper.ItemMapper;
import com.kreitek.store.application.service.service.ItemService;
import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.persistence.ItemPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemPersistence persistence;
    private final ItemMapper mapper;

    @Autowired
    public ItemServiceImpl(ItemPersistence persistence, ItemMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = this.persistence.getAllItems();
        return mapper.toDto(items);
    }

    @Override
    public List<ItemDTO> getAllItemsByCategory(Long id) {
        List<Item> items = this.persistence.getAllItemsByCategory(id);
        return this.mapper.toDto(items);
    }

    @Override
    public Optional<ItemDTO> getItemById(Long id) {
        return this.persistence.getItemById(id).map(mapper::toDto);
    }

    @Override
    public ItemDTO saveItem(ItemDTO item) {
        Item itemsave = this.persistence.saveItem(this.mapper.toEntity(item));
        return this.mapper.toDto(itemsave);

    }

    @Override
    public void deleteItem(Long id) {
        this.persistence.deleteItem(id);
    }

    @Override
    public Page<ItemDTO> getItemByCriteriaStringPaged(Pageable pageable, String filter) {
        Page<Item> itemPage = this.persistence.findAll(pageable,filter);
        return itemPage.map(mapper::toDto);
    }


}
