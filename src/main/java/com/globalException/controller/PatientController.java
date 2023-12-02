package com.globalException.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalException.model.Patient;
import com.globalException.service.PatientService;
import com.globalException.utility.RecordNotFoundException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/patientGlobalException")
public class PatientController {


    @Autowired
    PatientService patientService;

   /* @Autowired
    RestTemplate restTemplate;*/


    //Posting and saving the data on here by using the post method.

    @PostMapping("/savePatient")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient result = patientService.save(patient);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping("/findPatientDetails")
    public ResponseEntity<List<Patient>> FindAllPatientDetails() {
        List<Patient> reslut = patientService.findAll(PageRequest.of(0,20));
       return ResponseEntity
                .status(HttpStatus.OK)
                .body(reslut);
    }

    //We are fetching the data one here by using the By name

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<Patient>> findByName (@PathVariable String name) throws RecordNotFoundException {
        List<Patient> result = patientService.findByName(name);
        return ResponseEntity
                .ok()
                .body(result);
    }



        //Getting the data on here by using the rest template and from Student application..

    @GetMapping("/getPatientData")
    public ResponseEntity<String> getAllStudentData() {

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> httpEntity = new HttpEntity<>("");
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/GetStudent", HttpMethod.GET, httpEntity, String.class);
        return response;

        //HttpHeaders httpHeaders1 = new HttpHeaders();
    }

        //Saving and posting the patient data by using the rest template and storing also data Student Application..
    @PostMapping("/savePatientData")
    public ResponseEntity<String> saveStudentData(@RequestBody Patient patient) throws JSONException, JsonProcessingException {
         patientService.saveStudent(patient);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Posting the data By Map through.

        HashMap hashMap  = new HashMap();
        hashMap.put("id", patient.getId());
        hashMap.put("name", patient.getName());
        hashMap.put("email", patient.getEmail());

        //Sending on here DATA by json format type data.

       /* JSONObject objectJson = new JSONObject();
        objectJson.put("id",patient.getId());
        objectJson.put("name",patient.getName());
        objectJson.put("email",patient.getEmail());*/


        String jsonObject = new ObjectMapper().writeValueAsString(hashMap);
        HttpEntity<String> httpEntity = new HttpEntity<String> (jsonObject.toString(),httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/saveStudent", HttpMethod.POST, httpEntity, String.class);
        return response;


    }
}