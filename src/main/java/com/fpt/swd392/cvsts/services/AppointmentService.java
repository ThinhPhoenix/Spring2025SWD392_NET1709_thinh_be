package com.fpt.swd392.cvsts.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.swd392.cvsts.dto.TemplateDoseIntervalDTO;
import com.fpt.swd392.cvsts.dto.request.VaccineRegistrationRequest;
import com.fpt.swd392.cvsts.entities.Appointment;
import com.fpt.swd392.cvsts.entities.AppointmentDetail;
import com.fpt.swd392.cvsts.repositories.AppointmentDetailRepository;
import com.fpt.swd392.cvsts.repositories.AppointmentRepository;
import com.fpt.swd392.cvsts.repositories.VaccinationTemplateRepository;
import com.fpt.swd392.cvsts.utils.Utils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppointmentService implements IAppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentDetailRepository appointmentDetailRepository;

    @Autowired
    private VaccinationTemplateRepository vaccinationTemplateRepository;

    @Override
    public Appointment createAppointment(VaccineRegistrationRequest request) {
        try {

            Appointment appointment = new Appointment(Utils.generateUUID(), request.getVaccinePackageId(),
                    request.getNotes(), request.getTotalPrice(), request.getVaccinationRecordId());

            List<AppointmentDetail> appointmentDetails = new ArrayList<>();

            if (request.getVaccinePackageId() != null
                    && (request.getVaccineId() == "" || request.getVaccineId() == null)) {
                List<Object[]> results = vaccinationTemplateRepository
                        .getTemplateDoseIntervals(request.getVaccinePackageId());
                List<TemplateDoseIntervalDTO> templateDoseIntervalDTOs = converTemplateDoseIntervalDTOs(results);
                AppointmentDetail firstDose = new AppointmentDetail(Utils.generateUUID(),
                        templateDoseIntervalDTOs.get(0).getFromVaccineId(), appointment.getId(), "package",
                        templateDoseIntervalDTOs.get(0).getFromDoseNumber(), request.getScheduleDate(),
                        request.getTimeFrom(), "PENDING", LocalDateTime.now(), LocalDateTime.now());

                appointmentDetails.add(firstDose);

                for (int i = 0; i < templateDoseIntervalDTOs.size(); i++) {
                    AppointmentDetail nextDose = new AppointmentDetail(Utils.generateUUID(),
                            templateDoseIntervalDTOs.get(i).getToVaccineId(), appointment.getId(), "package",
                            templateDoseIntervalDTOs.get(i).getToDoseNumber(),
                            appointmentDetails.get(i).getScheduledDate()
                                    .plusDays(templateDoseIntervalDTOs.get(i).getDaysBetween()),
                            request.getTimeFrom(), "PENDING", LocalDateTime.now(), LocalDateTime.now());

                    appointmentDetails.add(nextDose);
                }
                appointmentRepository.save(appointment);
                appointmentDetailRepository.saveAll(appointmentDetails);
                return appointment;
            } else if ((request.getVaccinePackageId() == null || request.getVaccinePackageId() == "")
                    && request.getVaccineId() != null) {
                AppointmentDetail appointmentDetail = new AppointmentDetail();
                appointmentDetail.setId(Utils.generateUUID().toString());
                appointmentDetail.setVaccineId(request.getVaccineId());
                appointmentDetail.setAppointmentId(appointment.getId());
                appointmentDetail.setDoseNumber(1);
                appointmentDetail.setType("single");
                appointmentDetail.setScheduledDate(request.getScheduleDate());
                appointmentDetail.setTimeFrom(request.getTimeFrom());
                appointmentDetail.setStatus("PENDING");
                appointmentDetail.setCreatedAt(LocalDateTime.now());
                appointmentDetail.setUpdatedAt(LocalDateTime.now());
                appointmentRepository.save(appointment);
                appointmentDetailRepository.save(appointmentDetail);
                return appointment;
            } else {
                throw new Exception("Invalid request");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error at createAppointment in AppointmentService: " + e);
        }
    }

    public List<TemplateDoseIntervalDTO> converTemplateDoseIntervalDTOs(List<Object[]> results) {
        List<TemplateDoseIntervalDTO> templateDoseIntervalDTOs = results.stream().map(vpd -> {
            TemplateDoseIntervalDTO templateDoseIntervalDTO = new TemplateDoseIntervalDTO();
            templateDoseIntervalDTO.setFromVaccineId(vpd[0].toString());
            templateDoseIntervalDTO.setFromDoseNumber(Integer.parseInt(vpd[1].toString()));
            templateDoseIntervalDTO.setToVaccineId(vpd[2].toString());
            templateDoseIntervalDTO.setToDoseNumber(Integer.parseInt(vpd[3].toString()));
            templateDoseIntervalDTO.setDaysBetween(Integer.parseInt(vpd[4].toString()));
            return templateDoseIntervalDTO;
        }).collect(Collectors.toList());
        return templateDoseIntervalDTOs;
    }
}