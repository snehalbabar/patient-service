package com.example.patientService.controller;

import com.example.patientService.dto.PatientRequestDto;
import com.example.patientService.dto.PatientResponseDto;
import com.example.patientService.dto.vaildators.createPatientValidationGroup;
import com.example.patientService.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class, createPatientValidationGroup.class})
                                                                @RequestBody PatientRequestDto request) {

        PatientResponseDto patientResponseDto = patientService.createPatient(request);
        return ResponseEntity.ok().body(patientResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id,
                                                           @Validated({Default.class}) @RequestBody PatientRequestDto requestDto){

      PatientResponseDto patientResponseDto =  patientService.updatePatient(id,requestDto);
      return  ResponseEntity.ok().body(patientResponseDto);

    }
}
