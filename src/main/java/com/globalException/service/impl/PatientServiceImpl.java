package com.globalException.service.impl;

import com.globalException.model.Patient;
import com.globalException.repository.PatientRepo;
import com.globalException.service.PatientService;
import com.globalException.utility.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepo patientRepo;

    @Override
    public Patient saveStudent(Patient student) {
        return patientRepo.save(student);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepo.save(patient);
    }


    @Override
    public List<Patient> findByName(String name) {
        List<Patient> patRecord = patientRepo.findByName(name);

            if (patRecord.isEmpty() || patRecord == null) {
            throw new RecordNotFoundException();
        }else
        return patRecord;
    }


    @Override
    public List<Patient> findAll(PageRequest pageable) {
        return patientRepo.findAll();
    }


}