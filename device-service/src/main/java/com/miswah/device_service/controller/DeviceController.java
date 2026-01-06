package com.miswah.device_service.controller;


import com.miswah.device_service.dto.DeviceDto;
import com.miswah.device_service.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService){
        this.deviceService = deviceService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getDevice(@PathVariable Long id){
        return new ResponseEntity<>(this.deviceService.getDevice(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto dto){
        return new ResponseEntity<>(this.deviceService.createDevice(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDevice(@PathVariable Long id, @RequestBody DeviceDto dto){
        try {
            this.deviceService.updateDevice(id, dto);
            return new ResponseEntity<>("Device updated", HttpStatus.OK);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>("Device not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedevice(@PathVariable Long id){
        try {
            this.deviceService.deleteDevice(id);
            return new ResponseEntity<>("Device Deleted", HttpStatus.OK);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>("Device not found", HttpStatus.NOT_FOUND);
        }
    }
}
