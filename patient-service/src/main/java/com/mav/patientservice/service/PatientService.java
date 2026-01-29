package com.mav.patientservice.service;

import com.mav.patientservice.dto.PatientRequestDTO;
import com.mav.patientservice.dto.PatientResponseDTO;
import com.mav.patientservice.exception.EmailAlreadyExistsException;
import com.mav.patientservice.exception.PatientNotFoundException;
import com.mav.patientservice.grpc.BillingServiceGrpcClient;
import com.mav.patientservice.mapper.PatientMapper;
import com.mav.patientservice.model.Patient;
import com.mav.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    
    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    
    PatientService(PatientRepository patientRepository,
                   BillingServiceGrpcClient billingServiceGrpcClient) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
    }
    
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }
    
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "A patient with this email already exists: " + patientRequestDTO.getEmail());
        }
        
        Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        
        billingServiceGrpcClient.createBillingAccount(patient.getId().toString(), patient.getName(),
                patient.getEmail());
        
        return PatientMapper.toDTO(patient);
    }
    
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient with id " + id + " not found"));
        
        if (!patient.getEmail()
                .equals(patientRequestDTO.getEmail()) && patientRepository.existsByEmail(
                patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "A patient with this email already exists: " + patientRequestDTO.getEmail());
        }
        
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setBirthDate(LocalDate.parse(patientRequestDTO.getBirthDate()));
        
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }
    
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
    
}
