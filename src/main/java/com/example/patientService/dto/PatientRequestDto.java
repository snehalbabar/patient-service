package com.example.patientService.dto;

import com.example.patientService.dto.vaildators.createPatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.internal.engine.groups.Group;

import javax.swing.*;

@Getter
@Setter
public class PatientRequestDto {

    @NotBlank(message = "name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;
    @NotBlank(message = "email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "date of birth is required")
    private String DateOfBirth;

    @NotBlank(groups = createPatientValidationGroup.class,
            message = "register date is required")
    private String registeredDate;

}
