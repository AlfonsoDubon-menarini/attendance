package com.attendance.application.port.out;

import com.attendance.domain.model.Attendance;

public interface AttendanceOutputPort {
    void send(Attendance attendance);
}
