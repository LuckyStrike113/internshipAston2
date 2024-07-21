package by.servletsCRUDApi.service;

import by.servletsCRUDApi.dto.EmployeeDTO;
import by.servletsCRUDApi.filter.EmployeeFilter;
import by.servletsCRUDApi.model.Employee;
import by.servletsCRUDApi.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class EmployeeServiceTest {

    private final EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);

    @InjectMocks
    private EmployeeService employeeService;
    private EmployeeDTO employeeDTO;
    private Employee employee;
    private int employeeId;
    private String employeeFirstname;
    private String employeeLastname;
    private String mail;
    private int salary;
    private int companyId;
    private EmployeeFilter filter;
    @BeforeEach
    void setUp() {
        employee = new Employee();
        employeeId = 1;
        employeeFirstname = "name";
        employeeLastname = "lastname";
        mail = "mail";
        salary = 1111;
        companyId = 2;
        filter = new EmployeeFilter();
        employeeDTO = new EmployeeDTO();
        employee.setFirstname(employeeFirstname);
        employeeDTO.setFirstname(employeeFirstname);
        employee.setId(employeeId);
        employeeDTO.setId(employeeId);
        employee.setLastname(employeeLastname);
        employeeDTO.setLastname(employeeLastname);
        employee.setCompanyId(companyId);
        employeeDTO.setCompanyId(companyId);
        employee.setMail(mail);
        employeeDTO.setMail(mail);
        employee.setSalary(salary);
        employeeDTO.setSalary(salary);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }
    @Test
    @DisplayName("Get employee by id test")
    public void findByIdTest() {
        doReturn(employee).when(employeeRepository).findById(employeeId);
        EmployeeDTO employeeDTO = employeeService.findById(employeeId);

        assertEquals(employeeId, employeeDTO.getId());
        assertEquals(employeeFirstname, employeeDTO.getFirstname());
        assertEquals(employeeLastname, employeeDTO.getLastname());
        assertEquals(mail, employeeDTO.getMail());
        assertEquals(salary, employeeDTO.getSalary());
        assertEquals(companyId, employeeDTO.getCompanyId());

    }

    @Test
    @DisplayName("Get all employees test")
    public void findAllTest() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        doReturn(employees).when(employeeRepository).findAll(filter);
        List<EmployeeDTO> employeeDTOList = employeeService.findAll(filter);

        assertEquals(employees.size(), employeeDTOList.size());
    }

    @Test
    @DisplayName("Create new employee")
    public void saveTest() {
        doReturn(employee).when(employeeRepository).save(any());
        EmployeeDTO saveDTO = employeeService.save(employeeDTO);

        assertEquals(employeeId, saveDTO.getId());
        assertEquals(employeeFirstname, saveDTO.getFirstname());
        assertEquals(employeeLastname, employeeDTO.getLastname());
        assertEquals(mail, employeeDTO.getMail());
        assertEquals(salary, employeeDTO.getSalary());
        assertEquals(companyId, employeeDTO.getCompanyId());
    }

    @Test
    @DisplayName("Create new employee")
    public void updateTest() {
        doReturn(employee).when(employeeRepository).update(any());
        EmployeeDTO updateDTO = employeeService.update(employeeDTO);

        assertEquals(employeeId, updateDTO.getId());
        assertEquals(employeeFirstname, updateDTO.getFirstname());
    }

    @Test
    @DisplayName("Create new employee")
    public void deleteTest() {

        employeeService.delete(employeeId);

        verify(employeeRepository).delete(employeeId);
    }

}
