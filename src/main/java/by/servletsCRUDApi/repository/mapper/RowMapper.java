package by.servletsCRUDApi.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T mapRow(ResultSet resultSet, int rowNumber) throws SQLException;

}
