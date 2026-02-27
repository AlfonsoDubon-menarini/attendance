package com.attendance.infrastructure.adapter.input.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AttendanceRequest(
    @Schema(description = "Código único del usuario (Estudiante, Administrativo o Catedrático)", example = "EST-12345", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    String employeeId,

    @Schema(description = "ID del aula o centro administrativo", example = "AULA-204")
    @NotBlank
    String locationId,

    @Schema(description = "Fecha y hora del registro (ISO 8601)", example = "2026-02-27T08:00:00")
    @NotNull
    LocalDateTime checkInTime,

    @Schema(description = "Fecha y hora de salida opcional (ISO 8601)", example = "2026-02-27T17:00:00")
    LocalDateTime checkOutTime,

    @Schema(description = "Estado de la asistencia (PRESENT, LATE, ABSENT)", example = "PRESENT")
    String status
) {}
