package com.Acme.game.service.impl;


import com.Acme.game.model.Employee;
import com.Acme.game.model.SecretSantaAssignment;
import com.Acme.game.service.contact.AssignmentStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    private final AssignmentStrategy strategy;

    public AssignmentService(AssignmentStrategy strategy) {
        this.strategy = strategy;
    }

    public List<SecretSantaAssignment> generateAssignmentsBaseOnLastYear(List<Employee> employees, List<SecretSantaAssignment> lastYearAssignments) {
        return strategy.assignUsingLastYear(employees, lastYearAssignments);
    }
    public List<SecretSantaAssignment> generateAssignmentsBaseOnEmploye(List<Employee> employees) {
        return strategy.assignUsingEmploye(employees);
    }
}
