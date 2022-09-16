package org.cache.controller;

import org.cache.model.EmployeeData;
import org.cache.model.EmployeeResponse;
import org.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cache")
public class CacheController {
    /*
    in-order to check the cache -> hit Get call and see 2nd time query would not be executable.
     */
    private final EmployeeService employeeService;

    @Autowired
    public CacheController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody EmployeeData data) {

        Optional<EmployeeResponse> res = employeeService.saveEmployeeData(data);
        return res.map(employeeResponse -> new ResponseEntity<>(employeeResponse, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeByID(@PathVariable("id") Integer id) {

        Optional<EmployeeResponse> res = employeeService.getEmployeeById(id);
        return res.map(employeeResponse -> new ResponseEntity<>(employeeResponse, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployeeData() {

        List<EmployeeResponse> res = employeeService.getAllEmployees();
        return Optional.of(res).map(employeeResponse -> new ResponseEntity<>(employeeResponse, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

}
