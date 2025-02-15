package com.Acme.game.service.contact;

import com.Acme.game.model.Employee;
import com.Acme.game.model.SecretSantaAssignment;

import java.util.List;

public interface AssignmentStrategy {
    List<SecretSantaAssignment> assignUsingLastYear(List<Employee> employees, List<SecretSantaAssignment> lastYearAssignments);
     List<SecretSantaAssignment> assignUsingEmploye(List<Employee> employees) ;

    }

