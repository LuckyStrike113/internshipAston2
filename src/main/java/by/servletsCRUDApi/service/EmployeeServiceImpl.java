package by.servletsCRUDApi.service;

import by.servletsCRUDApi.dto.EmployeeDTO;
import by.servletsCRUDApi.filter.EmployeeFilter;
import by.servletsCRUDApi.mapper.EmployeeMapper;
import by.servletsCRUDApi.model.Employee;
import by.servletsCRUDApi.repository.EmployeeRepository;
import by.servletsCRUDApi.repository.EmployeeRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryImpl();
    }

    @Override
    public EmployeeDTO findById(int id) {
        Employee byId = employeeRepository.findById(id);
        return EmployeeMapper.MAPPER.toDTO(byId);
    }

    @Override
    public List<EmployeeDTO> findAll(EmployeeFilter employeeFilter) {
        return employeeRepository.findAll(employeeFilter)
                .stream().map(EmployeeMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        return EmployeeMapper.MAPPER.toDTO(employeeRepository.save(EmployeeMapper.MAPPER.toEntity(employeeDTO)));
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        return EmployeeMapper.MAPPER.toDTO(employeeRepository.update(EmployeeMapper.MAPPER.toEntity(employeeDTO)));
    }

    @Override
    public void delete(int id) {
        employeeRepository.delete(id);
    }
}
