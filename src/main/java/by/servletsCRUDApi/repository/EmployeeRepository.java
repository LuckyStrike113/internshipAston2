package by.servletsCRUDApi.repository;

import by.servletsCRUDApi.filter.EmployeeFilter;
import by.servletsCRUDApi.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee findById(int id);
    List<Employee> findAll(EmployeeFilter employeeFilter);
    Employee save(Employee employee);
    Employee update(Employee employee);
    void delete (int id);
}
