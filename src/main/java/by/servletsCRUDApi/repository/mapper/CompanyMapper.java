package by.servletsCRUDApi.repository.mapper;

import by.servletsCRUDApi.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper implements RowMapper<Company> {

    @Override
    public Company mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Company company = new Company();
        if (resultSet.next()) {
            company.setId(resultSet.getInt("C.ID"));
            company.setName(resultSet.getString("C.NAME"));
        }
        return company;
    }
}
