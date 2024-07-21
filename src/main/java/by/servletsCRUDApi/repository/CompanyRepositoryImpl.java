package by.servletsCRUDApi.repository;

import by.servletsCRUDApi.repository.mapper.CompanyListMapper;
import by.servletsCRUDApi.repository.mapper.CompanyMapper;
import by.servletsCRUDApi.repository.mapper.RowMapper;
import by.servletsCRUDApi.config.DataBaseConnection;
import by.servletsCRUDApi.model.Company;

import java.sql.*;
import java.util.List;

public class CompanyRepositoryImpl implements CompanyRepository {

    private final Connection connection;

    public CompanyRepositoryImpl() {
        this.connection = DataBaseConnection.getConnection();
    }

    @Override
    public Company findById(int id) {
        Company company;
        try {
            String sql = "        SELECT C.ID, " +
                    "                    C.NAME " +
                    "            FROM COMPANY AS C " +
                    "            WHERE C.ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            RowMapper<Company> mapper = new CompanyMapper();
            company = mapper.mapRow(resultSet, 0);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return company;
    }

    @Override
    public List<Company> findAll() {

        List<Company> companyList;
        try {
            String sql = "SELECT * FROM COMPANY";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            RowMapper<List<Company>> mapper = new CompanyListMapper();
            companyList = mapper.mapRow(resultSet, 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companyList;
    }

    @Override
    public Company save(Company company) {
        Company saved = null;
        try {
            String sql = "INSERT INTO COMPANY (NAME) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, company.getName());
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
    public Company update(Company company) {
        Company update;
        try {
            String sql = "UPDATE COMPANY SET NAME = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, company.getName());
            preparedStatement.setInt(2, company.getId());
            preparedStatement.executeUpdate();
            update = findById(company.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }


    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM COMPANY WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
