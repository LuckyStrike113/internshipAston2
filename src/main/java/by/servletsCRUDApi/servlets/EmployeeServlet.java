package by.servletsCRUDApi.servlets;

import by.servletsCRUDApi.dto.EmployeeDTO;
import by.servletsCRUDApi.filter.EmployeeFilter;
import by.servletsCRUDApi.service.EmployeeService;
import by.servletsCRUDApi.service.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/employee/*")
public class EmployeeServlet extends HttpServlet {

    public static final String CONTENT_TYPE = "application/json";
    private final EmployeeService employeeService;
    private final ObjectMapper objectMapper;
    public EmployeeServlet() {
        this.employeeService = new EmployeeServiceImpl();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")){
            EmployeeFilter employeeFilter = new EmployeeFilter();
            String companyId = req.getParameter("companyId");
            employeeFilter.setCompanyId(companyId != null ? Integer.parseInt(companyId) : null);
            writer.write(objectMapper.writeValueAsString(employeeService.findAll(employeeFilter)));
        } else {
            String[] pathInfo = req.getPathInfo().split("/");
            writer.write(objectMapper.writeValueAsString(employeeService.findById(Integer.parseInt(pathInfo[1]))));
        }
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        BufferedReader reader = req.getReader();
        String json = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        EmployeeDTO employeeDTO = objectMapper.readValue(json, EmployeeDTO.class);
        EmployeeDTO save = employeeService.save(employeeDTO);
        writer.write(objectMapper.writeValueAsString(save));
        writer.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        BufferedReader reader = req.getReader();
        String json = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        EmployeeDTO employeeDTO = objectMapper.readValue(json, EmployeeDTO.class);
        EmployeeDTO update = employeeService.update(employeeDTO);
        writer.write(objectMapper.writeValueAsString(update));
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        String[] pathInfo = req.getPathInfo().split("/");
        int id = Integer.parseInt(pathInfo[pathInfo.length -1]);
        employeeService.delete(id);
    }
}
