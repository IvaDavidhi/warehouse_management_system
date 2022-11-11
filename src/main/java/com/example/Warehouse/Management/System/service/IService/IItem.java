package com.example.Warehouse.Management.System.service.IService;

import com.example.Warehouse.Management.System.model.InventoryItems;
import com.example.Warehouse.Management.System.payload.dto.ItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IItem {

    Page<InventoryItems> listAllItems(Pageable pageable);

    InventoryItems getItemById(Long id);

    InventoryItems saveNewItem(ItemDto item);

    void deleteItemById(Long id);

    InventoryItems updateItemById(ItemDto item, Long id);
}
