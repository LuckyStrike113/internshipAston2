package by.servletsCRUDApi.repository.mapper;

import by.servletsCRUDApi.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Employee employee = new Employee();
        if(resultSet.next()){
            employee.setId(resultSet.getInt("id"));
            employee.setFirstname(resultSet.getString("firstname"));
            employee.setLastname(resultSet.getString("lastname"));
            employee.setMail(resultSet.getString("mail"));
            employee.setSalary(resultSet.getInt("salary"));
            employee.setCompanyId(resultSet.getInt("company_id"));
        }
        return employee;
    }
}
