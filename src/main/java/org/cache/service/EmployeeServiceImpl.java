package org.cache.service;

import org.cache.model.Employee;
import org.cache.model.EmployeeData;
import org.cache.model.EmployeeResponse;
import org.cache.repository.CacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "myEmployeeCache")
public class EmployeeServiceImpl implements EmployeeService {

    private final CacheRepository repository;

    // @CacheEvict(value="ticketsCache",key="#ticketId")    for delete
    // @CachePut(value="ticketsCache",key="#ticketId") for update

    @Autowired
    public EmployeeServiceImpl(CacheRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<EmployeeResponse> saveEmployeeData(EmployeeData employeeData) {

        Employee employee = new Employee(employeeData);
        Employee savedData = repository.save(employee);
        return Optional.of(new EmployeeResponse(savedData));
    }

    @Override
    @Cacheable(value = "myEmployeeCache", key = "#employeeId", unless = "#result==null")
    public Optional<EmployeeResponse> getEmployeeById(Integer employeeId) {
        Optional<Employee> e = repository.findById(employeeId);
        AtomicReference<EmployeeResponse> res = new AtomicReference<>(new EmployeeResponse());
        e.ifPresent(m -> res.set(new EmployeeResponse(m)));
        return Optional.ofNullable(res.get());
    }

    @Override
    @Cacheable(value = "myEmployeeCache", key = "#emp_Id", unless = "#result==null")
    public List<EmployeeResponse> getAllEmployees() {
        AtomicReference<EmployeeResponse> response = new AtomicReference<>(new EmployeeResponse());
        return repository.findAll().stream().map(e -> {
            response.set(new EmployeeResponse(e));
            return response.get();
        }).collect(Collectors.toList());
    }


}
