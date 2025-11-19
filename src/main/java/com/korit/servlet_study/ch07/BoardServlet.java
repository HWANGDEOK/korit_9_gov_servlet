package com.korit.servlet_study.ch07;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/boards")
public class BoardServlet extends HttpServlet {
    private BoardRepository boardRepository;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        boardRepository = BoardRepository.getInstance();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Board> boards = boardRepository.findAll();
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        objectMapper.writeValue(resp.getWriter(), boards);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");

        Board board = objectMapper.readValue(req.getInputStream(), Board.class);
        boardRepository.insert(board);

        Response response = Response.builder()
                .message("게시글 작성 완료")
                .build();
        objectMapper.writeValue(resp.getWriter(), response);
    }
}
