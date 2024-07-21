package by.servletsCRUDApi.mapper;

import by.servletsCRUDApi.dto.CompanyDTO;
import by.servletsCRUDApi.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {

    CompanyMapper MAPPER = Mappers.getMapper(CompanyMapper.class);

    CompanyDTO toDTO(Company company);

    Company toEntity(CompanyDTO companyDTO);
}