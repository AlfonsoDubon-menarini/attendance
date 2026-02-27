package com.attendance.infrastructure.adapter.input.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.UUID;

public record AttendanceResponse(
        @Schema(description = "Identificador único del registro de asistencia", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,

        @Schema(description = "Código único del usuario (Estudiante, Administrativo o Catedrático)", example = "EST-12345")
        String employeeId,

        @Schema(description = "ID del aula o centro administrativo", example = "AULA-204")
        String locationId,

        @Schema(description = "Fecha y hora del registro de entrada", example = "2026-02-27T08:00:00")
        LocalDateTime checkInTime,

        @Schema(description = "Fecha y hora del registro de salida", example = "2026-02-27T17:00:00")
        LocalDateTime checkOutTime,

        @Schema(description = "Estado actual de la asistencia", example = "PRESENT")
        String status
) {}
