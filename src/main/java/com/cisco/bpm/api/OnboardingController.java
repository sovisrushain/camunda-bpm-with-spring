package com.cisco.bpm.api;

import com.cisco.bpm.entity.Employee;
import com.cisco.bpm.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/onboarding")
public class OnboardingController {

    private final RuntimeService runtimeService;
    private final EmployeeRepository employeeRepository;

    @PostMapping("/start")
    public String startOnboarding(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        Map<String, Object> variables = new HashMap<>();
        variables.put("employeeId", employee.getId());
        runtimeService.startProcessInstanceByKey("employeeOnboardingProcess", variables);
        return "Onboarding process started for employee: " + employee.getName();
    }

}
