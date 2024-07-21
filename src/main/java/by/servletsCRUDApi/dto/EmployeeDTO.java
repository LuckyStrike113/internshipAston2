package by.servletsCRUDApi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmployeeDTO {
    private int id;
    private String firstname;
    private String lastname;
    private String mail;
    private int salary;
    private int companyId;
}
