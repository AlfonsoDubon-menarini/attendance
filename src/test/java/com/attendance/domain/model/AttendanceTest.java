package com.attendance.domain.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceTest {

    @Test
    void shouldSetDefaultStatusToPresentWhenNull() {
        // Arrange
        LocalDateTime checkIn = LocalDateTime.now();
        LocalDateTime checkOut = checkIn.plusHours(8);

        // Act
        Attendance attendance = new Attendance(
                "1", "EMP-001", "LOC-01", checkIn, checkOut, null
        );

        // Assert
        assertEquals("PRESENT", attendance.status(), "The default status should be PRESENT");
        assertEquals("LOC-01", attendance.locationId());
    }

    @Test
    void shouldThrowExceptionWhenCheckInTimeIsAfterCheckOutTime() {
        // Arrange
        LocalDateTime checkIn = LocalDateTime.now();
        LocalDateTime checkOut = checkIn.minusHours(1);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Attendance("2", "EMP-002", "LOC-02", checkIn, checkOut, "PRESENT");
        });

        assertEquals("Check-in time cannot be after check-out time", exception.getMessage());
    }

    @Test
    void shouldCreateAttendanceWhenDataIsValid() {
        // Arrange
        LocalDateTime checkIn = LocalDateTime.now();
        LocalDateTime checkOut = checkIn.plusHours(5);

        // Act
        Attendance attendance = new Attendance(
                "3", "EMP-003", "LOC-03", checkIn, checkOut, "PRESENT"
        );

        // Assert
        assertNotNull(attendance);
        assertEquals("PRESENT", attendance.status());
    }
}
