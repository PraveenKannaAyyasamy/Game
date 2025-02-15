package com.Acme.game.service.impl;

import com.Acme.game.exception.DataNotFoundException;
import com.Acme.game.model.Employee;
import com.Acme.game.model.SecretSantaAssignment;
import com.Acme.game.service.factory.RandomAssignmentStrategy;
import org.apache.commons.csv.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVService {


    public byte[] generateCSV(MultipartFile employee, MultipartFile lastYear) throws IOException {

        List<SecretSantaAssignment> newAssignments=null;
        byte[] csvBytes=null;
        if (employee.isEmpty()) {
            throw new DataNotFoundException("Employees file is required.");
        }

        try{

            List<Employee> employees = parseEmployees(employee.getInputStream());

            if (lastYear != null) {
                List<SecretSantaAssignment> lastYearAssignments = parsePreviousAssignments(lastYear.getInputStream());
                AssignmentService assignmentService = new AssignmentService(new RandomAssignmentStrategy());
                newAssignments = assignmentService.generateAssignmentsBaseOnLastYear(employees, lastYearAssignments);

            } else {
                AssignmentService assignmentService = new AssignmentService(new RandomAssignmentStrategy());
                newAssignments = assignmentService.generateAssignmentsBaseOnEmploye(employees);
            }
             csvBytes = writeAssignmentsToBytes(newAssignments);

        }catch(FileNotFoundException e){
            throw new FileNotFoundException("Employees file not found.");
        }catch(IOException e){
            throw  new IOException("Error reading employees file.");
        }
        return csvBytes;
    }


    public List<Employee> parseEmployees(InputStream inputStream) throws IOException {
        List<Employee> employees = new ArrayList<>();
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                employees.add(new Employee(record.get("Employee_Name"), record.get("Employee_EmailID")));
            }
        }
        if (employees.size() < 3) {
            throw new IllegalArgumentException("At least three employees are required for Secret Santa.");
        }
        return employees;
    }

    public List<SecretSantaAssignment> parsePreviousAssignments(InputStream inputStream) throws IOException {
        List<SecretSantaAssignment> assignments = new ArrayList<>();
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                assignments.add(new SecretSantaAssignment(
                        record.get("Employee_Name"),
                        record.get("Employee_EmailID"),
                        record.get("Secret_Child_Name"),
                        record.get("Secret_Child_EmailID")
                ));
            }
        }
        return assignments;
    }

    public byte[] writeAssignmentsToBytes(List<SecretSantaAssignment> assignments) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("Employee_Name", "Employee_EmailID", "Secret_Child_Name", "Secret_Child_EmailID"))) {

            for (SecretSantaAssignment assignment : assignments) {
                csvPrinter.printRecord(assignment.getEmployeeName(), assignment.getEmployeeEmail(),
                        assignment.getSecretChildName(), assignment.getSecretChildEmail());
            }
            csvPrinter.flush();
            return outputStream.toByteArray();
        }
    }


}

