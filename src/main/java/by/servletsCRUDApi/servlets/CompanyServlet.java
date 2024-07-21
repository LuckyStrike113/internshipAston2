package by.servletsCRUDApi.servlets;

import by.servletsCRUDApi.dto.CompanyDTO;
import by.servletsCRUDApi.service.CompanyService;
import by.servletsCRUDApi.service.CompanyServiceImpl;
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

@WebServlet("/company/*")
public class CompanyServlet extends HttpServlet {

    public static final String CONTENT_TYPE = "application/json";
    private final CompanyService companyService;
    private final ObjectMapper objectMapper;
    public CompanyServlet() {
        this.companyService = new CompanyServiceImpl();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType(CONTENT_TYPE);
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")){
            writer.write(objectMapper.writeValueAsString(companyService.findAll()));
        } else {
            String[] pathInfo = req.getPathInfo().split("/");
            writer.write(objectMapper.writeValueAsString(companyService.findById(Integer.parseInt(pathInfo[1]))));
        }
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType(CONTENT_TYPE);
        BufferedReader reader = req.getReader();
        String json = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        CompanyDTO companyDTO = objectMapper.readValue(json, CompanyDTO.class);
        CompanyDTO save = companyService.save(companyDTO);
        writer.write(objectMapper.writeValueAsString(save));
        writer.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType(CONTENT_TYPE);
        BufferedReader reader = req.getReader();
        String json = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        CompanyDTO companyDTO = objectMapper.readValue(json, CompanyDTO.class);
        CompanyDTO update = companyService.update(companyDTO);
        writer.write(objectMapper.writeValueAsString(update));
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        String[] pathInfo = req.getPathInfo().split("/");
        int id = Integer.parseInt(pathInfo[pathInfo.length -1]);
        companyService.delete(id);
    }
}
