package by.servletsCRUDApi.service;

import by.servletsCRUDApi.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    CompanyDTO findById(int id);
    List<CompanyDTO> findAll();
    CompanyDTO save(CompanyDTO companyDTO);
    CompanyDTO update(CompanyDTO companyDTO);
    void delete (int id);
}
