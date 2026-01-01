package com.miswah.user_service.service;


import com.miswah.user_service.dto.UserDto;
import com.miswah.user_service.entity.User;
import com.miswah.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public UserDto createUser(UserDto dto){
        log.info("Creating user : {}", dto);

        return this.mapToDto(this.userRepository.save(this.mapToEntity(dto)));
    }

    public void updateUser(Long id, UserDto dto){
        log.info("Update User {}", id);
        log.info("Updating data {}", dto);

        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not found"));

        user.setName(dto.name());
        user.setSurname(dto.surname());
        user.setEmail(dto.email());
        user.setAddress(dto.address());
        user.setAlerting(dto.alerting());
        user.setEnergyAlertingThreshold(dto.energyAlertingThreshold());
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not found"));

        this.userRepository.delete(user);
    }

    public UserDto getUser(Long id){
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not found"));
        return this.mapToDto(user);
    }

    private User mapToEntity(UserDto dto){
        User user = new User();
        user.setName(dto.name());
        user.setSurname(dto.surname());
        user.setEmail(dto.email());
        user.setAlerting(dto.alerting());
        user.setEnergyAlertingThreshold(dto.energyAlertingThreshold());

        return user;
    }

    private UserDto mapToDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getAddress(), user.isAlerting(), user.getEnergyAlertingThreshold() );
    }
}
