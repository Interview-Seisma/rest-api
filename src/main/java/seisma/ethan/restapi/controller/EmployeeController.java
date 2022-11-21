package seisma.ethan.restapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seisma.ethan.restapi.model.Employee;
import seisma.ethan.restapi.model.EmployeeOutput;
import seisma.ethan.restapi.utils.EmployeeUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
    private List<EmployeeOutput> employeeOutputs = new ArrayList<>();
    private EmployeeUtil util = new EmployeeUtil();

    @PostMapping("/calculate")
    public ResponseEntity<?> create(@RequestBody List<Employee> employeeList) {
        employeeOutputs.clear();
        employeeList.forEach(e -> {
            employeeOutputs.add(util.calculateEmployeeDetail(e));
        });
        return ResponseEntity.ok(employeeOutputs);
    }
}
