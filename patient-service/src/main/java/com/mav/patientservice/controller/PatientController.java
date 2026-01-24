package com.mav.patientservice.controller;

import com.mav.patientservice.dto.PatientResponseDTO;
import com.mav.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
