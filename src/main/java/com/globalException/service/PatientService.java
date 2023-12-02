package com.globalException.service;

import com.globalException.model.Patient;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {

    Patient saveStudent(Patient student);

    Patient save(Patient patient);
    

    List<Patient> findByName(String name);


    List<Patient> findAll(PageRequest pageable);
}
