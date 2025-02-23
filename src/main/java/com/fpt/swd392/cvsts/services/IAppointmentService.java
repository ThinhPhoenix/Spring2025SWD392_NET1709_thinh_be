package com.fpt.swd392.cvsts.services;

import com.fpt.swd392.cvsts.dto.request.VaccineRegistrationRequest;
import com.fpt.swd392.cvsts.entities.Appointment;

public interface IAppointmentService {
    public Appointment createAppointment(VaccineRegistrationRequest request);
}
