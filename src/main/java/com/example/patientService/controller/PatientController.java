package com.example.patientService.controller;

import com.example.patientService.dto.PatientRequestDto;
import com.example.patientService.dto.PatientResponseDto;
import com.example.patientService.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @GetMapping()
    public ResponseEntity<List<PatientResponseDto>> getPatients() {

        List<PatientResponseDto> patientsList = patientService.getPatient();
        return ResponseEntity.ok().body(patientsList);

    }

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody
                                                            PatientRequestDto request) {

        PatientResponseDto patientResponseDto = patientService.createPatient(request);
        return ResponseEntity.ok().body(patientResponseDto);
    }
}
