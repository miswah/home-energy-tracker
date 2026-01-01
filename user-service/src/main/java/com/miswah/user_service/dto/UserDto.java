package com.miswah.user_service.dto;

public record UserDto(Long id, String name, String surname, String email, String address, boolean alerting, double energyAlertingThreshold) {
}
