package com.bloom.pium.controller;

import com.bloom.pium.data.dto.BoardDto;
import com.bloom.pium.data.dto.BoardResponseDto;
import com.bloom.pium.data.dto.ModifyBoardDto;

import com.bloom.pium.data.repository.CategoryRepository;
import com.bloom.pium.data.repository.CommentRepository;
import com.bloom.pium.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    private final CommentRepository commentRepository;
    private final CategoryRepository categoryRepository;

    @Autowired


    public BoardController(BoardService boardService, CommentRepository commentRepository, CategoryRepository categoryRepository) {
        this.boardService = boardService;
        this.commentRepository = commentRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping()
    public ResponseEntity<BoardResponseDto> getBoard(Long boardId) {
        BoardResponseDto boardResponseDto = boardService.getBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @PostMapping("/write")
    public ResponseEntity<BoardResponseDto> createdBoard(@RequestBody BoardDto boardDto) {
        BoardResponseDto boardResponseDto = boardService.saveBoard(boardDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @PutMapping("/modify")
    public ResponseEntity<BoardResponseDto> modifyBoard(
            @RequestBody ModifyBoardDto modifyBoard) throws Exception {
        BoardResponseDto boardResponseDto = boardService.modifyBoard(
                modifyBoard.getBoardId(),
                modifyBoard.getTitle(),
                modifyBoard.getContent());

        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }
}