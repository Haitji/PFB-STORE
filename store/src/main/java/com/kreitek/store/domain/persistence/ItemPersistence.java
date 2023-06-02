package com.kreitek.store.domain.persistence;

import com.kreitek.store.domain.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ItemPersistence {
    List<Item> getAllItems();
    List<Item> getAllItemsByCategory(Long id);
    Optional<Item> getItemById(Long id);
    Item saveItem(Item item);
    void deleteItem(Long id);

    Page<Item> findAll(Pageable pageable, String filter);
}
