package com.Acme.game.service.factory;

import com.Acme.game.model.Employee;
import com.Acme.game.model.SecretSantaAssignment;
import com.Acme.game.service.contact.AssignmentStrategy;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class RandomAssignmentStrategy implements AssignmentStrategy {
    @Override
    public List<SecretSantaAssignment> assignUsingLastYear(List<Employee> employees, List<SecretSantaAssignment> lastYearAssignments) {
        List<SecretSantaAssignment> assignments = new ArrayList<>();
        Set<String> lastYearPairs = new HashSet<>();
        for (SecretSantaAssignment a : lastYearAssignments) {
            lastYearPairs.add(a.getEmployeeEmail() + "-" + a.getSecretChildEmail());
        }

        List<Employee> receivers = new ArrayList<>(employees);
        Collections.shuffle(receivers);

        for (Employee giver : employees) {
            for (Employee receiver : receivers) {
                if (!giver.getEmail().equals(receiver.getEmail()) &&
                        !lastYearPairs.contains(giver.getEmail() + "-" + receiver.getEmail())) {
                    assignments.add(new SecretSantaAssignment(giver.getName(),giver.getEmail(), receiver.getName(),receiver.getEmail()));
                    receivers.remove(receiver);
                    break;
                }
            }
        }
        return assignments;
    }

    public List<SecretSantaAssignment> assignUsingEmploye(List<Employee> employees) {
        List<Employee> receivers = new ArrayList<>(employees);
        Collections.shuffle(receivers);

        List<SecretSantaAssignment> assignments = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            Employee giver = employees.get(i);
            Employee receiver = receivers.get(i);
            if (giver.getEmail().equals(receiver.getEmail())) {
                Collections.shuffle(receivers);
                i = -1;
                continue;
            }
            assignments.add(new SecretSantaAssignment(giver.getName(), giver.getEmail(), receiver.getName(), receiver.getEmail()));
        }
        return assignments;
    }
}


