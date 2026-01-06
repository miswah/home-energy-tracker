package com.miswah.device_service.entity;


import com.miswah.device_service.model.DeviceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "device")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Device {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @Column
    private String location;

    @Column
    private Long userId;
}
