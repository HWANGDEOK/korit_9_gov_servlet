package com.korit.servlet_study.ch07;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BoardRepository {
    private static BoardRepository instance;
    private List<Board> boards;

    private BoardRepository() {
        boards = new ArrayList<>();
    }

    public static BoardRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new BoardRepository();
        }
        return instance;
    }

    public void insert(Board board) {
        boards.add(board);
    }

    public List<Board> findAll() {
        return boards;
    }
}
