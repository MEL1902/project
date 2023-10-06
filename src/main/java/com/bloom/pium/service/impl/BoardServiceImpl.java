package com.bloom.pium.service.impl;

import com.bloom.pium.data.dto.BoardDto;
import com.bloom.pium.data.dto.BoardResponseDto;
import com.bloom.pium.data.entity.Board;
import com.bloom.pium.data.entity.Category;
import com.bloom.pium.data.repository.BoardRepository;
import com.bloom.pium.data.repository.CategoryRepository;
import com.bloom.pium.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardServiceImpl  implements BoardService {
    private final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);

    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository; // CategoryRepository 주입
    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, CategoryRepository categoryRepository) {
        this.boardRepository = boardRepository;
        this.categoryRepository = categoryRepository;
    }



    // 조회
    @Override
    public BoardResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setTitle(board.getTitle());
        boardResponseDto.setContent(board.getContent());
        boardResponseDto.setViewCnt(board.getViewCnt());
        boardResponseDto.setLikeCnt(board.getLikeCnt());

        return boardResponseDto;
    }

    @Override
    public BoardResponseDto saveBoard(BoardDto boardDto) {
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setSchedule(boardDto.getSchedule());
        board.setPlace(boardDto.getPlace());
        board.setViewCnt(0); // 초기값으로 0 설정
        board.setLikeCnt(0);
        board.setCreatedDate(LocalDateTime.now());
        board.setModifiedDate(LocalDateTime.now());

// 카테고리 설정
        Category category = categoryRepository.findById(boardDto.getCategoryId()).orElse(null);
        board.setCategory(category);

        Board savedBoard = boardRepository.save(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setBoardId(savedBoard.getBoardId());
        boardResponseDto.setTitle(savedBoard.getTitle());
        boardResponseDto.setContent(savedBoard.getContent());

        return boardResponseDto;
    }
    @Override
    public BoardResponseDto modifyBoard(Long boardId, String title, String content) throws Exception {
        Board foundBoard = boardRepository.findById(boardId).get();
        foundBoard.setTitle(title);
        foundBoard.setContent(content);

        Board changedBoard = boardRepository.save(foundBoard);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setBoardId(changedBoard.getBoardId());
        boardResponseDto.setTitle(changedBoard.getTitle());
        boardResponseDto.setContent(changedBoard.getContent());

        return boardResponseDto;
    }

    @Override
    public void deletBoard(Long boardId) throws Exception {

    }
}
