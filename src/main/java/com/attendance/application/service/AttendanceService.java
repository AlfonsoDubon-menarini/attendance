package com.attendance.application.service;

import com.attendance.application.port.in.CompleteAttendanceUseCase;
import com.attendance.application.port.in.RegisterAttendanceUseCase;
import com.attendance.application.port.out.AttendanceOutputPort;
import com.attendance.application.port.out.AttendancePersistencePort;
import com.attendance.domain.model.Attendance;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AttendanceService implements RegisterAttendanceUseCase, CompleteAttendanceUseCase {

    private final AttendancePersistencePort persistencePort;
    private final AttendanceOutputPort outputPort;

    // Inyección de dependencias por constructor
    public AttendanceService(AttendancePersistencePort persistencePort, AttendanceOutputPort outputPort) {
        this.persistencePort = persistencePort;
        this.outputPort = outputPort;
    }

    @Override
    public Attendance register(Attendance attendance) {
        // 1. Aquí a futuro pondremos reglas de negocio (ej. validar si ya tiene check-in)

        // 2. Guardamos en la base de datos usando el puerto de salida
        Attendance savedAttendance = persistencePort.save(attendance);

        // 3. Notificamos al Gateway externo (ficticio)
        outputPort.send(savedAttendance);

        // 4. Retornamos la entidad guardada (con su UUID generado) al controlador
        return savedAttendance;
    }

    @Override
    public Attendance execute(UUID id, LocalDateTime checkOutTime) {
        // 1. Buscar el registro existente
        Attendance attendance = persistencePort.findById(String.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Registro de asistencia no encontrado"));

        // 2. Aplicar lógica de dominio para completar la salida
        // Creamos un nuevo record con los datos actualizados ya que Attendance es inmutable
        Attendance updatedAttendance = new Attendance(
                attendance.id(),
                attendance.employeeId(),
                attendance.locationId(),
                attendance.checkInTime(),
                checkOutTime,
                "COMPLETED"
        );

        // 3. Guardar cambios
        return persistencePort.save(updatedAttendance);
    }
}
