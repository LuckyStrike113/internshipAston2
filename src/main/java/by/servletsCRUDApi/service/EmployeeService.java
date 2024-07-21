package by.servletsCRUDApi.service;

import by.servletsCRUDApi.dto.EmployeeDTO;
import by.servletsCRUDApi.filter.EmployeeFilter;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO findById(int id);
    List<EmployeeDTO> findAll(EmployeeFilter employeeFilter);
    EmployeeDTO save(EmployeeDTO employeeDTO);
    EmployeeDTO update(EmployeeDTO employeeDTO);
    void delete (int id);
}
