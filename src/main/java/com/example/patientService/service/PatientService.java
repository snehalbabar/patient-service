package com.example.patientService.service;

import com.example.patientService.dto.PatientRequestDto;
import com.example.patientService.dto.PatientResponseDto;
import com.example.patientService.exception.EmailAlreadyExistsException;
import com.example.patientService.exception.PatientNotFoundException;
import com.example.patientService.mapper.PatientMapper;
import com.example.patientService.model.Patient;
import com.example.patientService.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<PatientResponseDto> getPatient(){
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(PatientMapper::toDTO).toList();
    }


    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {

        if (patientRepository.existsByEmail(patientRequestDto.getEmail())) {
            throw new EmailAlreadyExistsException("email already exists" + patientRequestDto.getEmail());
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDto));

        //
        return PatientMapper.toDTO(newPatient);

    }

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto){
        Patient patient = patientRepository.findById(id).orElseThrow(
                ()-> new PatientNotFoundException("Patient not found with Id:" + id));

        if (patientRepository.existsByEmail(patientRequestDto.getEmail())) {
            throw new PatientNotFoundException("email already exists" + patientRequestDto.getEmail());
        }
        patient.setName(patientRequestDto.getName());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));

       Patient upadatedPatient = patientRepository.save(patient);
       return PatientMapper.toDTO(upadatedPatient);
    }


}
