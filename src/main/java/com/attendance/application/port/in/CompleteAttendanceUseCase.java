package com.attendance.application.port.in;

import com.attendance.domain.model.Attendance;
import java.time.LocalDateTime;
import java.util.UUID;

public interface CompleteAttendanceUseCase {
    Attendance execute(UUID id, LocalDateTime checkOutTime);
}
