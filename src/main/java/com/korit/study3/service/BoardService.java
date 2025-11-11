package com.korit.study3.service;

import com.korit.study3.dao.BoardDao;
import com.korit.study3.dto.BoardReqDto;
import com.korit.study3.entity.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardService {
    private static  BoardService instance;
    private BoardDao boardDao;

    private BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public static BoardService getInstance() {
        if (instance  == null) {
            instance = new BoardService(BoardDao.getInstance());
        }
        return instance;
    }

    public boolean isDupleTitle(String title) {
        Optional<Board> searchTitle = boardDao.searchTitleByTitle(title);
        return searchTitle.isPresent();
    }

    public void insertBoard(BoardReqDto boardReqDto) {
        Board board = boardReqDto.toEntity();
        boardDao.addBoard(board);
    }

    public Board searchBoardByBoardId(Integer boardId) {
        return boardDao.searchBoardById(boardId).get();
    }

    public List<Board> searchBoardByUsername(String username) {
        return boardDao.searchBoardByUsername(username);
    }

    public List<Board> searchBoardByKeyword(String keyword) {
        return boardDao.searchBoardByKeyword(keyword);
    }



}
