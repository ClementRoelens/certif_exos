package org.example.exothymeleaf.controller;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exothymeleaf.service.PersonService;

import java.io.IOException;

@WebServlet(name = "person" , value = "/person")
public class PersonServlet extends HttpServlet {
    private PersonService personService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        personService = new PersonService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("persons", personService.getPersons());
        req.getRequestDispatcher("/views/person.jsp").forward(req, resp);
    }
}
