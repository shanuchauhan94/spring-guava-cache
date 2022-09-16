package org.cache.service;

import org.cache.model.Employee;
import org.cache.model.EmployeeData;
import org.cache.model.EmployeeResponse;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<EmployeeResponse> saveEmployeeData(EmployeeData employeeData);

    Optional<EmployeeResponse> getEmployeeById(Integer employeeId);

    List<EmployeeResponse> getAllEmployees();
}
