package net.javaguides.springboot.service;

import net.javaguides.springboot.exception.EmployeeAlreadyExistException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.valueObjects.Department;
import net.javaguides.springboot.valueObjects.ResponsetemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Employee> getAllEmp(){
        return repository.findAll();
    }

    public Employee getEmpByID(Long id){
        return  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " +id));
        //return ResponseEntity.ok(employee);
    }

    public int deleteAllEmp(){
        repository.deleteAll();
        return 1;

    }

    public Employee saveEmp(Employee employee){
        return repository.save(employee);
    }

//    public Employee saveEmp(Employee employee){
//
//        Employee existingEmployee = repository.findById(employee.getId()).orElse(null);
//        if (existingEmployee == null){
//            return repository.save(employee);
//        }
//        else throw new EmployeeAlreadyExistException("Employee already exist in database");
//    }

    public List<Employee> saveListofEmp(List<Employee> list){

        return repository.saveAll(list);
    }


//    public ResponseEntity<Employee> updateImp(Long id, Employee employee){
//
//        Employee updateEmployee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: "+id));
//        updateEmployee.setFirstname(employee.getFirstname());
//        updateEmployee.setLastname(employee.getLastname());
//        updateEmployee.setEmailid(employee.getEmailid());
//        updateEmployee.setDepartmentId(employee.getDepartmentId());
//        updateEmployee.setActivityStatus(employee.getActivityStatus());
//        repository.save(updateEmployee);
//        return  ResponseEntity.ok(updateEmployee);
//
//    }

    public ResponseEntity<Employee> updateImp(Long id, Employee employee){

        Employee existingEmp = repository.findById(employee.getId()).orElse(null);
        if (existingEmp == null){
            throw new ResourceNotFoundException("No such employee exists");
        }

        else {
            existingEmp.setFirstname(employee.getFirstname());
            existingEmp.setLastname(employee.getLastname());
            existingEmp.setEmailid(employee.getEmailid());
            existingEmp.setDepartmentId(employee.getDepartmentId());
            existingEmp.setActivityStatus(employee.getActivityStatus());
            repository.save(existingEmp);
            return  ResponseEntity.ok(existingEmp);
        }
    }

    public Employee deleteEmp(Long id){

        Employee employee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not find by the id: "+id));
        repository.delete(employee);
        return employee;
    }

    public ResponsetemplateVO getUserWithDep(Long empId) {

        ResponsetemplateVO vo = new ResponsetemplateVO();
        Employee employee = repository.findById(empId).orElseThrow(()-> new ResourceNotFoundException("Employee not found by the id: "+empId));
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+employee.getDepartmentId(), Department.class);
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(employee);
        vo.setEmployeeList(employeeList);
//        List<Employee> employeeList;
//        new  ArrayList<>().add(employee);
//        vo.setEmployeeList(employeeList);
//        vo.setEmployee(employee);
        vo.setDepartment(department);
        return vo;

    }

    public ResponsetemplateVO getAllEmpOfDep(String name) {

        ResponsetemplateVO vo = new ResponsetemplateVO();
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/dep/"+name, Department.class);
        List<Employee> employeeList = repository.findAllDepartmentEmployees(department.getDepartmentId());
        vo.setDepartment(department);
        vo.setEmployeeList(employeeList);
        return vo;
    }

//    public List<Employee> getAllActiveEmp() {
//
//       return repository.findAllActiveUsers();
//    }
//
//    public List<Employee> getAllBackendEmp() {
//
//        return repository.findAllBackendEmployees();
//    }
//
//    public List<Employee> getAllFrontendEmp() {
//
//        return repository.findAllFrontendEmployees();
//    }
//
//    public List<Employee> getAllDevopsEmp() {
//
//        return repository.findAllDevopsEmployees();
//    }
//
//    public List<Employee> getAllAutomationEmp() {
//
//        return repository.findAllAutomationEmployees();
//    }
//
//    public List<Employee> getAllFullstackEmp() {
//
//        return repository.findAllFullStackEmployees();
//    }
//
//    public List<Employee> getAllNonActiveEmp() {
//
//        return repository.findAllNonActiveUsers();
//    }

    public List<Employee> getAllDepartmentEmp(long id) {

        return repository.findAllDepartmentEmployees(id);
    }


    public List<Employee> getEmpByStatus(String status) {

        return repository.findEmpByStatus(status);
    }
}
