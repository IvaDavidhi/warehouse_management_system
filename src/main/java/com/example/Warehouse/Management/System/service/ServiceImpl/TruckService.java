package com.example.Warehouse.Management.System.service.ServiceImpl;

import com.example.Warehouse.Management.System.exceptions.ResourceNotFoundException;
import com.example.Warehouse.Management.System.model.Truck;
import com.example.Warehouse.Management.System.payload.dto.TruckDto;
import com.example.Warehouse.Management.System.repository.TruckRepository;
import com.example.Warehouse.Management.System.service.IService.ITruck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class TruckService implements ITruck {

    @Autowired
    private TruckRepository truckRepository;

    @Override
    public List<Truck> listAllTrucks() {
        return truckRepository.findAll();
    }

    @Override
    public Truck getTruckById(Long id) {
        return truckRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Truck by id " + id + " was not found"));
    }

    @Override
    public Truck saveNewTruck(TruckDto truck) {
        log.info("Saving new truck {} to the database", truck.getChassisNumber());
        Truck newTruck = new Truck(truck.getChassisNumber(),
                truck.getLicensePlate());
        return truckRepository.save(newTruck);
    }

    @Override
    public void deleteTruckById(Long id) {
        truckRepository.deleteById(id);
    }

    @Override
    public Truck updateTruckById(TruckDto truck, Long id) {
        Truck existingTruck = truckRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Truck not found"));
        existingTruck.setLicensePlate(truck.getLicensePlate());

        return existingTruck;
    }
}
