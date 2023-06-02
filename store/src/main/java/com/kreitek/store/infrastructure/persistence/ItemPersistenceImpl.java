package com.kreitek.store.infrastructure.persistence;

import com.kreitek.store.domain.entity.Item;
import com.kreitek.store.domain.persistence.ItemPersistence;
import com.kreitek.store.infrastructure.specs.ItemSpecification;
import com.kreitek.store.infrastructure.specs.shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

@Repository
public class ItemPersistenceImpl implements ItemPersistence {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemPersistenceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAllItems() {
        return this.itemRepository.findAll();
    }

    @Override
    public List<Item> getAllItemsByCategory(Long id) {
        return this.itemRepository.findAllByCategoryId(id);
    }

    @Override
    public Optional<Item> getItemById(Long id) {
        return this.itemRepository.findById(id);
    }

    @Override
    public Item saveItem(Item item) {
        return this.itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        this.itemRepository.deleteById(id);
    }

    @Override
    public Page<Item> findAll(Pageable pageable, String filter) {
        ItemSpecification specification = new ItemSpecification(SearchCriteriaHelper.fromFilterString(filter));
        return this.itemRepository.findAll(specification,pageable);
    }

}
