package by.servletsCRUDApi.repository;

import by.servletsCRUDApi.model.Company;

import java.util.List;

public interface CompanyRepository {
    Company findById(int id);
    List<Company> findAll();
    Company save(Company company);
    Company update(Company company);
    void delete (int id);
}
