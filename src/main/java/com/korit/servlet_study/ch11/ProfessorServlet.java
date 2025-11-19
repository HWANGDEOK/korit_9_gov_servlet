package com.korit.servlet_study.ch11;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.ch11.Service.ProfessorService;
import com.korit.servlet_study.ch11.dao.ProfessorDao;
import com.korit.servlet_study.ch11.entity.Professor;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/professors")
public class ProfessorServlet extends HttpServlet {

    private ProfessorService professorService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // q 파람 뽑아내기
        String q = req.getParameter("q");
        ProfessorService professorService = new ProfessorService();
        List<Professor>  professors = professorService.getProfessors(q);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), professors);
    }
}
