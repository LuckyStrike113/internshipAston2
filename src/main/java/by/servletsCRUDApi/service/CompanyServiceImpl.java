package by.servletsCRUDApi.service;

import by.servletsCRUDApi.dto.CompanyDTO;
import by.servletsCRUDApi.mapper.CompanyMapper;
import by.servletsCRUDApi.model.Company;
import by.servletsCRUDApi.repository.CompanyRepository;
import by.servletsCRUDApi.repository.CompanyRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl() {
        this.companyRepository = new CompanyRepositoryImpl();
    }

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDTO findById(int id) {
        Company byId = companyRepository.findById(id);
        return CompanyMapper.MAPPER.toDTO(byId);
    }

    @Override
    public List<CompanyDTO> findAll() {
        return companyRepository.findAll()
                .stream().map(CompanyMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        return CompanyMapper.MAPPER.toDTO(companyRepository.save(CompanyMapper.MAPPER.toEntity(companyDTO)));
    }

    @Override
    public CompanyDTO update(CompanyDTO companyDTO) {
        return CompanyMapper.MAPPER.toDTO(companyRepository.update(CompanyMapper.MAPPER.toEntity(companyDTO)));
    }

    @Override
    public void delete(int id) {
        companyRepository.delete(id);
    }
}
