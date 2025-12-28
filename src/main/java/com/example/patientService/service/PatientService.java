package com.example.patientService.service;

import com.example.patientService.dto.PatientResponseDTO;
import com.example.patientService.mapper.PatientMapper;
import com.example.patientService.model.Patient;
import com.example.patientService.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<PatientResponseDTO> getPatient(){
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(PatientMapper::toDTO).toList();
    }


}
