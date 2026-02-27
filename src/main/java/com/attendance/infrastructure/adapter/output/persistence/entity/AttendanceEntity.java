package com.attendance.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String employeeId;

    @Column(nullable = false)
    private String locationId;

    @Column(nullable = false)
    private LocalDateTime checkInTime;

    @Column
    private LocalDateTime checkOutTime;

    @Column(nullable = false)
    private String status;
}
