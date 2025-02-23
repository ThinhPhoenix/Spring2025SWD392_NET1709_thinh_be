package com.fpt.swd392.cvsts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.swd392.cvsts.entities.AppointmentDetail;
import com.fpt.swd392.cvsts.repositories.AppointmentDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService implements ITransactionService {

    @Autowired
    private AppointmentDetailRepository appointmentDetailRepository;

    @Override
    public void createRefundRequest(String appointmentId) {
        //appointment_details.status -> REFUND_PENDING
        AppointmentDetail a = appointmentDetailRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("Appointment not found"));
        if (!a.getStatus().equals("COMPLETED")) {
            throw new RuntimeException("Appointment is not completed yet");
        }
        a.setStatus("REFUND_PENDING");
        appointmentDetailRepository.save(a);
    }

    @Override
    public void acceptRefundRequest(String appointmentId) {
        //appointment_details.status -> REFUND_ACCEPTED
        AppointmentDetail a = appointmentDetailRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("Appointment not found"));
        if (!a.getStatus().equals("REFUND_PENDING")) {
            throw new RuntimeException("Refund request is not pending");
        }
        a.setStatus("REFUND_ACCEPTED");
        appointmentDetailRepository.save(a);
    }
}
