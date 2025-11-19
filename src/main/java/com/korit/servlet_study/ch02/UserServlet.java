package com.korit.servlet_study.ch02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/ch02/users")
public class UserServlet extends HttpServlet {

    private List<User> users;

    @Override
    public void init() throws ServletException {
        users = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // username == "test"
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        List<User> foundusers = users.stream()
                .filter(user -> user.getUsername().equals(req.getParameter("username")))
                .toList();

        User foundUser = foundusers.isEmpty() ? null : foundusers.get(0);
        if (Objects.isNull(foundUser)) {
            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("해당 username은 존재하지않습니다.");
            return;
        }
        resp.getWriter().println(foundUser);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");


        users.add(new User(username, password, name, email));
        System.out.println(users);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("사용자 등록 완료");
    }
}