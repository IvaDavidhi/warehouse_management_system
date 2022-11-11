package com.example.Warehouse.Management.System.controller;


import com.example.Warehouse.Management.System.model.InventoryItems;
import com.example.Warehouse.Management.System.payload.dto.ItemDto;
import com.example.Warehouse.Management.System.service.ServiceImpl.ItemService;
;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/items")
@SecurityRequirement(name = "warehouse")
@PreAuthorize("hasAuthority('MANAGER')")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    ResponseEntity<Page<InventoryItems>> getItems(Pageable pageable) {
        return ResponseEntity.ok().body(itemService.listAllItems(pageable));
    }

    @GetMapping(value = "find/{id}")
    ResponseEntity<InventoryItems> getItemById(@PathVariable Long id) {
        return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    ResponseEntity<InventoryItems> saveNewItem(@RequestBody ItemDto newItem) {
        return ResponseEntity.ok().body(itemService.saveNewItem(newItem));
    }

    @PutMapping(value = "/update/{id}")
    ResponseEntity<InventoryItems> updateItem(@RequestBody ItemDto item, @PathVariable Long id) {
        return new ResponseEntity<>(itemService.updateItemById(item, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<?> deleteItemById(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
