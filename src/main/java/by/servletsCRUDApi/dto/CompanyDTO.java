package by.servletsCRUDApi.dto;

import by.servletsCRUDApi.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CompanyDTO {
    private int id;
    private String name;
}