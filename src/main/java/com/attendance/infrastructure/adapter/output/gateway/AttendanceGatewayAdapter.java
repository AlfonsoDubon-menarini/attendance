package com.attendance.infrastructure.adapter.output.gateway;

import com.attendance.application.port.out.AttendanceOutputPort;
import com.attendance.domain.model.Attendance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AttendanceGatewayAdapter implements AttendanceOutputPort {

    private static final Logger log = LoggerFactory.getLogger(AttendanceGatewayAdapter.class);
    private final RestClient gatewayRestClient;

    public AttendanceGatewayAdapter(RestClient gatewayRestClient) {
        this.gatewayRestClient = gatewayRestClient;
    }

    @Override
    public void send(Attendance attendance) {
        // Corrección 1: El log ahora usa employeeId en lugar de customerId
        log.info(">>>> Processing attendance notification in Gateway for Employee: {}", attendance.employeeId());

        try {
            gatewayRestClient.post()
                    // Corrección 2: Actualizamos el endpoint externo al nuevo dominio
                    .uri("/attendance/notify")
                    .contentType(MediaType.APPLICATION_JSON)
                    // Corrección 3: Pasamos el objeto attendance correctamente
                    .body(attendance)
                    .retrieve()
                    // Corrección 4: Como el puerto ahora retorna void (lo cambiamos en el commit anterior),
                    // usamos toBodilessEntity() en lugar de body(Attendance.class)
                    .toBodilessEntity();

            log.info(">>>> Notification sent successfully.");
        } catch (Exception e) {
            log.error(">>>> Gateway Connection Failed: {}", e.getMessage());
            // Como el método retorna void, simplemente registramos el error sin interrumpir el flujo principal.
        }
    }
}
