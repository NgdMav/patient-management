package com.mav.patientservice.mapper;

import com.mav.patientservice.dto.PatientResponseDTO;
import com.mav.patientservice.model.Patient;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient) {
        if (patient == null) {
            return null;
        }
        return new PatientResponseDTO(
                patient.getId().toString(),
                patient.getName(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getBirthDate().toString()
        );
    }
}
