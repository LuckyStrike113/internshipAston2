package by.servletsCRUDApi.repository.mapper;

import by.servletsCRUDApi.model.Company;
import by.servletsCRUDApi.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyListMapper implements RowMapper<List<Company>> {

    @Override
    public List<Company> mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        List<Company> companyList = new ArrayList<>();
        while (resultSet.next()) {
            Company company = new Company();
            company.setId(resultSet.getInt("id"));
            company.setName(resultSet.getString("name"));
            companyList.add(company);
        }
        return companyList;
    }
}
