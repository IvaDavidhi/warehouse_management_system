package com.example.Warehouse.Management.System.service.IService;

import com.example.Warehouse.Management.System.model.Truck;
import com.example.Warehouse.Management.System.payload.dto.TruckDto;

import java.util.List;

public interface ITruck {

    List<Truck> listAllTrucks();

    Truck getTruckById(Long id);

    Truck saveNewTruck(TruckDto truck);

    void deleteTruckById(Long id);

    Truck updateTruckById(TruckDto truck, Long id);
}
