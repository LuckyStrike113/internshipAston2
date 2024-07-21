package by.servletsCRUDApi.repository.mapper;

import by.servletsCRUDApi.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeListMapper implements RowMapper<List<Employee>>{

    public List<Employee> mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        List<Employee> employeeList = new ArrayList<>();

        while (resultSet.next()){
            Employee employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setFirstname(resultSet.getString("firstname"));
            employee.setLastname(resultSet.getString("lastname"));
            employee.setMail(resultSet.getString("mail"));
            employee.setSalary(resultSet.getInt("salary"));
            employee.setCompanyId(resultSet.getInt("company_id"));
            employeeList.add(employee);
        }
        return employeeList;
    }
}
