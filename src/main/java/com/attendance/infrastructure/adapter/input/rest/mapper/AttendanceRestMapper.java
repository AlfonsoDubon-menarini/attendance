package com.attendance.infrastructure.adapter.input.rest.mapper;

import com.attendance.domain.model.Attendance;
import com.attendance.infrastructure.adapter.input.rest.dto.AttendanceRequest;
import com.attendance.infrastructure.adapter.input.rest.dto.AttendanceResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AttendanceRestMapper {

    public Attendance toDomain(AttendanceRequest request) {
        return new Attendance(
                null,
                request.employeeId(),
                request.locationId(),
                request.checkInTime(),
                null,
                "PRESENT"
        );
    }

    public AttendanceResponse toResponse(Attendance domain) {
        return new AttendanceResponse(
                domain.id() != null ? UUID.fromString(domain.id()) : null,
                domain.employeeId(),
                domain.locationId(),
                domain.checkInTime(),
                domain.checkOutTime(),
                domain.status()
        );
    }
}
