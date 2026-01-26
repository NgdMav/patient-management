package com.mav.patientservice.controller;

import com.mav.patientservice.dto.PatientRequestDTO;
import com.mav.patientservice.dto.PatientResponseDTO;
import com.mav.patientservice.dto.validators.CreatePatientValidationGroup;
import com.mav.patientservice.service.PatientService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
class PatientController {
    
    private final PatientService patientService;
    
    PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }
    
    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody
            PatientRequestDTO patientRequestDTO) {
        return ResponseEntity.ok(patientService.createPatient(patientRequestDTO));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
                                                            @Validated({Default.class}) @RequestBody
                                                            PatientRequestDTO patientRequestDTO) {
        return ResponseEntity.ok(patientService.updatePatient(id, patientRequestDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
