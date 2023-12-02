package com.globalException.repository;

import com.example.student.model.Student;
import com.globalException.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {

    List<Patient> findByName(String name);

    Patient save(Student student);

}
