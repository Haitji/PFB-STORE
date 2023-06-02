package com.kreitek.store.infrastructure.rest;

import com.kreitek.store.application.dto.ItemDTO;
import com.kreitek.store.application.service.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @CrossOrigin
    @GetMapping(value = "/categories/{idCategory}/items",produces = "application/json")
    public ResponseEntity<List<ItemDTO>> getAllItems(@PathVariable Long idCategory){
        List<ItemDTO> itemDTOS = this.itemService.getAllItemsByCategory(idCategory);
        return new ResponseEntity<>(itemDTOS, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/items",produces = "application/json",consumes = "application/json")
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO){
        ItemDTO itemDTO1 = this.itemService.saveItem(itemDTO);
        return new ResponseEntity<>(itemDTO1,HttpStatus.CREATED);
    }

    @CrossOrigin
    @PatchMapping(value = "/items",produces = "application/json",consumes = "application/json")
    public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO){
        ItemDTO itemUpdate = this.itemService.saveItem(itemDTO);
        return new ResponseEntity<>(itemUpdate,HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/items-old",produces = "application/json")
    public ResponseEntity<List<ItemDTO>> getAllItems(){
        List<ItemDTO> itemDTOS = this.itemService.getAllItems();
        return new ResponseEntity<>(itemDTOS, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/items",produces = "application/json")
    public ResponseEntity<Page<ItemDTO>> getAllItemsByCriteriaPages(@RequestParam(value = "filter",required = false) String filter, Pageable pageable){

        Page<ItemDTO> itemDTOS = this.itemService.getItemByCriteriaStringPaged(pageable,filter);
        return new ResponseEntity<Page<ItemDTO>>(itemDTOS, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/items/{itemId}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long itemId){
        this.itemService.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/items/{itemId}",produces = "application/json")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Long itemId){

        Optional<ItemDTO> item = this.itemService.getItemById(itemId);
        if(item.isPresent()){
            return new ResponseEntity<>(item.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }
}
