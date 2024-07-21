package by.servletsCRUDApi.repository;

import by.servletsCRUDApi.config.DataBaseConnection;
import by.servletsCRUDApi.filter.EmployeeFilter;
import by.servletsCRUDApi.model.Employee;
import by.servletsCRUDApi.repository.mapper.EmployeeListMapper;
import by.servletsCRUDApi.repository.mapper.EmployeeMapper;
import by.servletsCRUDApi.repository.mapper.RowMapper;

import java.sql.*;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final Connection connection;

    public EmployeeRepositoryImpl() {
        this.connection = DataBaseConnection.getConnection();
    }

    @Override
    public Employee findById(int id) {
        Employee employee;
        try {
            String sql = "SELECT * FROM EMPLOYEES WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            RowMapper<Employee> mapper = new EmployeeMapper();
            employee = mapper.mapRow(resultSet, 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public List<Employee> findAll(EmployeeFilter employeeFilter) {

        List<Employee> employeeList;
        try {
            String sql = "SELECT * FROM EMPLOYEES";
            if (employeeFilter.getCompanyId() != null){
                sql += " WHERE COMPANY_ID = ?";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if (employeeFilter.getCompanyId() != null){
                preparedStatement.setInt(1, employeeFilter.getCompanyId());
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            EmployeeListMapper mapper = new EmployeeListMapper();
            employeeList = mapper.mapRow(resultSet, 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    @Override
    public Employee save(Employee employee) {
        Employee saved = null;
        try {
            String sql = "INSERT INTO EMPLOYEES (FIRSTNAME, LASTNAME, MAIL, SALARY, COMPANY_ID) VALUES (?, ?, ? ,?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getFirstname());
            preparedStatement.setString(2, employee.getLastname());
            preparedStatement.setString(3, employee.getMail());
            preparedStatement.setInt(4, employee.getSalary());
            preparedStatement.setInt(5, employee.getCompanyId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                int id = generatedKeys.getInt(1);
                saved = findById(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return saved;
    }

    @Override
    public Employee update(Employee employee) {
        Employee update;
        try {
            String sql = "UPDATE EMPLOYEES SET FIRSTNAME = ?, LASTNAME = ?, MAIL = ?, SALARY = ?, COMPANY_ID = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getFirstname());
            preparedStatement.setString(2, employee.getLastname());
            preparedStatement.setString(3, employee.getMail());
            preparedStatement.setInt(4, employee.getSalary());
            preparedStatement.setInt(5, employee.getCompanyId());
            preparedStatement.setInt(6, employee.getId());
            preparedStatement.executeUpdate();
            update = findById(employee.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }


    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM EMPLOYEES WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
