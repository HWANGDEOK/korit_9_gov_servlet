package com.korit.servlet_study.ch08;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/guide/boards")
public class BoardServlet extends HttpServlet {
    List<Board> boards = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");

        objectMapper.writeValue(resp.getWriter(), boards);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        BufferedReader bufferedReader = req.getReader();
        String json = "";
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) break;
            json += line;
        }
        System.out.println(json);

        Board board = objectMapper.readValue(json, Board.class);
        System.out.println(board.toString());
        boards.add(board);
        System.out.println(boards);

        Response response = Response.builder()
                .message("게시글 작성 완료")
                .build();

        resp.setContentType("application/json");
        objectMapper.writeValue(resp.getWriter(), response);
    }
}
