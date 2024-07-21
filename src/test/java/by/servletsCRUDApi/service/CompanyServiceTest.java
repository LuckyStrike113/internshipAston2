package by.servletsCRUDApi.service;

import by.servletsCRUDApi.dto.CompanyDTO;
import by.servletsCRUDApi.mapper.CompanyMapper;
import by.servletsCRUDApi.model.Company;
import by.servletsCRUDApi.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class CompanyServiceTest {

    private final CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);

    @InjectMocks
    private CompanyService companyService;
    private CompanyDTO companyDTO;
    private Company company;
    private int companyId;
    private String companyName;
    @BeforeEach
    void setUp() {
        company = new Company();
        companyId = 1;
        companyName = "asd";
        companyDTO = new CompanyDTO();
        company.setName(companyName);
        company.setId(companyId);
        companyDTO.setId(companyId);
        companyDTO.setName(companyName);
        companyService = new CompanyServiceImpl(companyRepository);
    }
    @Test
    @DisplayName("Get company by id test")
    public void findByIdTest() {
        doReturn(company).when(companyRepository).findById(companyId);
        CompanyDTO companyDTO = companyService.findById(companyId);

        assertEquals(companyId, companyDTO.getId());
        assertEquals("asd", companyDTO.getName());

    }

    @Test
    @DisplayName("Get all companies test")
    public void findAllTest() {
        List<Company> companies = new ArrayList<>();
        companies.add(company);

        doReturn(companies).when(companyRepository).findAll();
        List<CompanyDTO> companyDTOList = companyService.findAll();

        assertEquals(companies.size(), companyDTOList.size());
    }

    @Test
    @DisplayName("Create new company")
    public void saveTest() {
        doReturn(company).when(companyRepository).save(any());
        CompanyDTO saveDTO = companyService.save(companyDTO);

        assertEquals(companyId, saveDTO.getId());
        assertEquals(companyName, saveDTO.getName());
    }

    @Test
    @DisplayName("Create new company")
    public void updateTest() {
        doReturn(company).when(companyRepository).update(any());
        CompanyDTO updateDTO = companyService.update(companyDTO);

        assertEquals(companyId, updateDTO.getId());
        assertEquals(companyName, updateDTO.getName());
    }

    @Test
    @DisplayName("Create new company")
    public void deleteTest() {

        companyService.delete(companyId);

        verify(companyRepository).delete(companyId);
    }

}
