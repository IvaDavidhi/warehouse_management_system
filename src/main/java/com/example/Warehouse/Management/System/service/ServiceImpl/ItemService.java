package com.example.Warehouse.Management.System.service.ServiceImpl;

import com.example.Warehouse.Management.System.exceptions.ResourceNotFoundException;
import com.example.Warehouse.Management.System.model.InventoryItems;
import com.example.Warehouse.Management.System.payload.dto.ItemDto;
import com.example.Warehouse.Management.System.repository.InventoryItemsRepository;
import com.example.Warehouse.Management.System.service.IService.IItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemService implements IItem {

    @Autowired
    private InventoryItemsRepository itemsRepository;

    @Override
    public Page<InventoryItems> listAllItems(Pageable pageable) {

        return itemsRepository.findAll(pageable);
    }

    @Override
    public InventoryItems getItemById(Long id) {
        return itemsRepository.findById(id)
                       .orElseThrow(()-> new ResourceNotFoundException("Item by id " + id + " was not found"));
    }

    @Override
    public InventoryItems saveNewItem(ItemDto item) {
        log.info("Saving new item {} to the database", item.getItemName());
                InventoryItems newItem = new InventoryItems(item.getItemName(),
                        item.getItemPrice(),
                        item.getItemQuantity());
                return itemsRepository.save(newItem);
    }

    @Override
    public void deleteItemById(Long id) {
        itemsRepository.deleteById(id);
    }

    @Override
    public InventoryItems updateItemById(ItemDto item, Long id) {
        InventoryItems existingItem = itemsRepository.findById(id).orElseThrow(()->
                        new ResourceNotFoundException("Item not found"));
        existingItem.setItemName(item.getItemName());
        existingItem.setItemPrice(item.getItemPrice());
        existingItem.setItemQuantity(item.getItemQuantity());

        itemsRepository.save(existingItem);

        return existingItem;
    }
}
