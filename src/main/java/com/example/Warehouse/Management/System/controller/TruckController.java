package com.example.Warehouse.Management.System.controller;

import com.example.Warehouse.Management.System.model.Truck;
import com.example.Warehouse.Management.System.payload.dto.TruckDto;
import com.example.Warehouse.Management.System.service.ServiceImpl.TruckService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trucks")
@SecurityRequirement(name = "warehouse")
@PreAuthorize("hasAuthority('MANAGER')")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @GetMapping
    ResponseEntity<List<Truck>> getAllTrucks() {
        return ResponseEntity.ok().body(truckService.listAllTrucks());
    }

    @GetMapping(value = "/find/{id}")
    ResponseEntity<Truck> getTruckById(@PathVariable Long id) {
        return new ResponseEntity<>(truckService.getTruckById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    ResponseEntity<Truck> saveNewTruck(@RequestBody TruckDto newTruck) {
        return ResponseEntity.ok().body(truckService.saveNewTruck(newTruck));
    }

    @PutMapping(value = "/updateTruck/{id}")
    public ResponseEntity<Truck> updateTruck(@RequestBody TruckDto truck, @PathVariable Long id) {
        Truck updateTruck = truckService.updateTruckById(truck, id);
        return new ResponseEntity<>(updateTruck, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<?> deleteTruckById(@PathVariable Long id) {
        truckService.deleteTruckById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
