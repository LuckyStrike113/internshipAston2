package by.servletsCRUDApi.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonPropertyOrder({"id", "firstname", "lastname", "mail", "salary", "company_id"})
public class Employee {

    private int id;
    private String firstname;
    private String lastname;
    private String mail;
    private int salary;
    private int companyId;
}
