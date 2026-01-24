package com.mav.patientservice.service;

import com.mav.patientservice.dto.PatientResponseDTO;
import com.mav.patientservice.mapper.PatientMapper;
import com.mav.patientservice.model.Patient;
import com.mav.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }
}
