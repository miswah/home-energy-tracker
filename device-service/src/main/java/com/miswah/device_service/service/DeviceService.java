package com.miswah.device_service.service;


import com.miswah.device_service.dto.DeviceDto;
import com.miswah.device_service.entity.Device;
import com.miswah.device_service.exception.DeviceNotFoundException;
import com.miswah.device_service.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository){
        this.deviceRepository = deviceRepository;
    }

    public DeviceDto getDevice(Long id){
        Device device = this.deviceRepository.findById(id).orElseThrow(() -> new DeviceNotFoundException("Device not found"));
        return mapToDto(device);
    }

    public DeviceDto createDevice(DeviceDto dto){
        Device device = new Device();

        device.setName(dto.name());
        device.setType(dto.type());
        device.setLocation(dto.location());
        device.setUserId(dto.userId());

        this.deviceRepository.save(device);
        return mapToDto(device);
    }

    public void deleteDevice(Long id){
        Device device = this.deviceRepository.findById(id).orElseThrow(() -> new DeviceNotFoundException("Device not found"));

        this.deviceRepository.delete(device);
    }

    public void updateDevice(Long id, DeviceDto dto){
        Device device = this.deviceRepository.findById(id).orElseThrow(() -> new DeviceNotFoundException("Device not found"));

        device.setName(dto.name());
        device.setType(dto.type());
        device.setLocation(dto.location());
        device.setUserId(dto.userId());

        this.deviceRepository.save(device);
    }


    private DeviceDto mapToDto(Device device) {
        return new DeviceDto(device.getId(), device.getName(), device.getType(), device.getLocation(), device.getUserId());
    }
}
