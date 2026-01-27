package com.mav.patientservice.mapper;

import com.mav.patientservice.dto.PatientRequestDTO;
import com.mav.patientservice.dto.PatientResponseDTO;
import com.mav.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient) {
        if (patient == null) {
            return null;
        }
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(String.valueOf(patient.getId()));
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setDateOfBirth(String.valueOf(patient.getBirthDate()));
        return patientResponseDTO;
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO) {
        if (patientRequestDTO == null) {
            return null;
        }
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setBirthDate(LocalDate.parse(patientRequestDTO.getBirthDate()));
        patient.setRegistrationDate(LocalDate.parse(patientRequestDTO.getRegistrationDate()));
        return patient;
    }
}
